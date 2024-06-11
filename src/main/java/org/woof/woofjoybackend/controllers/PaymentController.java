package org.woof.woofjoybackend.controllers;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.entity.Parceiro;
import org.woof.woofjoybackend.domain.pagamento.PaymentRequest;
import org.woof.woofjoybackend.service.users.ServiceParceiro;
import org.woof.woofjoybackend.service.users.ServiceUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final ServiceUser serviceUser;
    private final ServiceParceiro serviceParceiro;

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @PostMapping()
    public ResponseEntity<Map<String, String>> createPayment(@RequestBody PaymentRequest paymentRequest, @RequestHeader("Authorization") String bearerToken) {
        //PEGANDO O USUARIO LOGADO
            Parceiro parceiro = serviceUser.getParceiroByToken(bearerToken);
            if (parceiro == null) {
                return ResponseEntity.notFound().build();
            }
            Integer idParceiro = parceiro.getUsuario().getId();

        Map<String, String> responseData = new HashMap<>();

        APIContext apiContext = new APIContext(clientId, clientSecret, mode);

        Amount amount = new Amount();
        amount.setCurrency("BRL");
        amount.setTotal(paymentRequest.getAmount());

        Transaction transaction = new Transaction();
        transaction.setDescription(paymentRequest.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/api/payment/cancel");
        redirectUrls.setReturnUrl("http://localhost:8080/api/payment/success/" + idParceiro);
        payment.setRedirectUrls(redirectUrls);

        try {
            Payment createdPayment = payment.create(apiContext);
            for (Links link : createdPayment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    responseData.put("redirectUrl", link.getHref());
                    break;
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar pagamento");
        }

        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("/success/{idParceiro}")
    public ResponseEntity<String> success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @PathVariable Integer idParceiro) {
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, mode);
            Payment payment = Payment.get(apiContext, paymentId);

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);
            Payment executedPayment = payment.execute(apiContext, paymentExecution);

            if ("approved".equals(executedPayment.getState())) {
                serviceParceiro.premiumParceiro(idParceiro);

                String successHtml = "<html>" +
                        "<head>" +
                        "<title>Pagamento Concluído</title>" +
                        "</head>" +
                        "<body>" +
                        "<h1>Pagamento concluído com sucesso!</h1>" +
                        "<p>Já pode fechar essa página.</p>" +
                        "</body>" +
                        "</html>";

                return ResponseEntity.ok().body(successHtml);
            } else {
                String failureHtml = "<html>" +
                        "<head>" +
                        "<title>Pagamento Não Aprovado</title>" +
                        "</head>" +
                        "<body>" +
                        "<h1>Pagamento não foi aprovado</h1>" +
                        "<p>Por favor, tente novamente.</p>" +
                        "<a href=\"http://localhost:3000/feed-parceiro-edit\">Voltar para a edição do parceiro</a>" +
                        "</body>" +
                        "</html>";

                return ResponseEntity.status(400).body(failureHtml);
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            String errorHtml = "<html>" +
                    "<head>" +
                    "<title>Erro no Pagamento</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Erro ao concluir o pagamento</h1>" +
                    "<p>Por favor, tente novamente mais tarde.</p>" +
                    "<a href=\"http://localhost:3000/feed-parceiro-edit\">Voltar para a edição do parceiro</a>" +
                    "</body>" +
                    "</html>";

            return ResponseEntity.status(400).body(errorHtml);
        }
    }


    @GetMapping("/cancel")
    public ResponseEntity<String> cancel() {
        return ResponseEntity.status(400).body("Pagamento foi cancelado");
    }
}
