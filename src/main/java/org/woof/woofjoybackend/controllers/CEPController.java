package org.woof.woofjoybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.woof.woofjoybackend.entity.response.Cep;
import org.woof.woofjoybackend.service.gateway.ServiceCEP;

@RestController
@RequestMapping("/cep")
public class CEPController {

    private ServiceCEP cepService;
    @Autowired
    CEPController(ServiceCEP cepService){
         this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Cep> buscar(@PathVariable String cep){
        Cep end = cepService.buscaCEP(cep);
        return ResponseEntity.status(200).body(end);
    }

}
