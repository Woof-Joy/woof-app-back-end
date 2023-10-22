package org.woof.woofjoybackend.controllers.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.woof.woofjoybackend.entity.response.CEP;
import org.woof.woofjoybackend.service.gateway.CEPService;

@RestController
@RequestMapping("/cep")
public class CEPController {
//    MUDO AQUI

    private CEPService cepService;
    @Autowired
    CEPController(CEPService cepService){
         this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CEP> bucar(@PathVariable String cep){
        CEP end = cepService.buscaCEP(cep);
        return ResponseEntity.status(200).body(end);
    }

}
