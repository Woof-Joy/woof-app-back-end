package org.woof.woofjoybackend.resources.webclient.gateway;

import org.springframework.stereotype.Component;
import org.woof.woofjoybackend.domain.entity.response.CepResponse;
@Component
public interface ClientCEPHttpGateway {
    public CepResponse getAddressByCep(String cep);
}
