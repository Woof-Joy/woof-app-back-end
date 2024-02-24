package org.woof.woofjoybackend.resources.webclient.gatewayImpl;

import feign.FeignException;
import org.springframework.stereotype.Component;
import org.woof.woofjoybackend.domain.entity.response.CepResponse;
import org.woof.woofjoybackend.resources.webclient.adapter.ClientCEPHttpConsumer;
import org.woof.woofjoybackend.resources.webclient.gateway.ClientCEPHttpGateway;

@Component
public class ClientCEPGatewayImpl implements ClientCEPHttpGateway {

    private final ClientCEPHttpConsumer clientCEPHttpConsumer;

    public ClientCEPGatewayImpl(ClientCEPHttpConsumer clientCEPHttpConsumer) {
        this.clientCEPHttpConsumer = clientCEPHttpConsumer;
    }

    @Override
    public CepResponse getAddressByCep(String cep) {
        try {
            return clientCEPHttpConsumer.getAdress(cep);
        } catch (FeignException ex) {
            throw new RuntimeException("Erro ao encontrar CEP informado", ex);
        }
    }
}
