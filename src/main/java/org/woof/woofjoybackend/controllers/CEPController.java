package org.woof.woofjoybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.woof.woofjoybackend.domain.entity.response.CepResponse;
import org.woof.woofjoybackend.resources.webclient.gatewayImpl.ClientCEPGatewayImpl;

@RestController
@RequestMapping("/api/cep")
public class CEPController {

    private ClientCEPGatewayImpl cepService;

    @Autowired
    CEPController(ClientCEPGatewayImpl cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepResponse> buscar(@PathVariable String cep) {
        CepResponse endereco = cepService.getAddressByCep(cep);
        return endereco.getLogradouro().isBlank()?
        ResponseEntity.status(404).build():
        ResponseEntity.status(200).body(endereco);
    }

}
