package org.woof.woofjoybackend.controllers;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.woof.woofjoybackend.domain.pagamento.PaymentRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @PostMapping()
    public Map<String, String> createPayment(@RequestBody PaymentRequest paymentRequest) {
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
        redirectUrls.setCancelUrl("https://www.google.com/");
        redirectUrls.setReturnUrl("https://www.youtube.com/");
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

        return responseData;
    }
}
