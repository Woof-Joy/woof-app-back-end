package org.woof.woofjoybackend.resources.webclient.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.woof.woofjoybackend.domain.entity.response.CepResponse;

@FunctionalInterface
@FeignClient(value = "CEP", url = "https://viacep.com.br/")
public interface ClientCEPHttpConsumer {
    @GetMapping(
            path = "ws/{cep}/json",
            headers = "Content-type=application/json"
    )
    CepResponse getAdress(@PathVariable String cep);
}
