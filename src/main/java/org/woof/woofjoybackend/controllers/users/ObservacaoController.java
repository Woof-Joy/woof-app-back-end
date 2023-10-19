package org.woof.woofjoybackend.controllers.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Observacao;
import org.woof.woofjoybackend.service.ServiceObservacao;

@RestController
@RequestMapping("/pet-observacao")
public class ObservacaoController {

    private ServiceObservacao serviceObservacao;

    public ObservacaoController(ServiceObservacao serviceObservacao) {
        this.serviceObservacao = serviceObservacao;
    }

    @PostMapping
    public ResponseEntity<Observacao> cadastrar (){

    }

    @PutMapping
    public ResponseEntity<Observacao> atulizar (){

    }


}
