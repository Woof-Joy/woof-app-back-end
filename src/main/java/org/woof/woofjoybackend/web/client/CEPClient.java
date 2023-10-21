package org.woof.woofjoybackend.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.woof.woofjoybackend.entity.response.CEP;

@FunctionalInterface
@FeignClient(value = "CEP", url = "https://viacep.com.br/")
public interface CEPClient {
    @GetMapping(path = "ws/{cep}/json",
    headers = "Content-type=application/json")
    public CEP buscaCep(@PathVariable String cep);
}
