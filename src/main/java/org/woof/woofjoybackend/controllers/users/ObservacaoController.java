package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Dog;
import org.woof.woofjoybackend.entity.Observacao;
import org.woof.woofjoybackend.service.ServiceObservacao;

@RestController
@RequestMapping("/pet-observacao")
public class ObservacaoController {

    private ServiceObservacao serviceObservacao;

    public ObservacaoController(ServiceObservacao serviceObservacao) {
        this.serviceObservacao = serviceObservacao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Observacao> listar(@PathVariable Integer id) {
        Observacao obs = serviceObservacao.listarObservacao(id);
        return obs == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(obs);
    }

    @PostMapping
    public ResponseEntity<Observacao> cadastrar (@Valid @RequestBody Observacao obs){
        Observacao obsCriada = serviceObservacao.cadastrar(obs);
        return ResponseEntity.ok().body(obsCriada);
    }

    @PutMapping
    public ResponseEntity<Observacao> atulizar (@Valid @RequestBody Observacao obs, int id){
        Observacao obsAtualizada = serviceObservacao.atulizar(id, obs);
        return obsAtualizada == null ? ResponseEntity.notFound().build():ResponseEntity.ok().body(obsAtualizada);
    }


}
