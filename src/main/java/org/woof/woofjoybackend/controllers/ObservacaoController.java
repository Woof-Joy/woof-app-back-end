package org.woof.woofjoybackend.controllers;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.ObservacaoDTO;
import org.woof.woofjoybackend.dto.mapper.ObservacaoMapper;
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
    public ResponseEntity<ObservacaoDTO> buscar(@PathVariable Integer id) {
        ObservacaoDTO obs = ObservacaoMapper.toDTO(serviceObservacao.listarObservacao(id));
        return obs == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(obs);
    }

    @PostMapping
    public ResponseEntity<ObservacaoDTO> cadastrar (@Valid @RequestBody Observacao obs){
        ObservacaoDTO obsCriada = ObservacaoMapper.toDTO(serviceObservacao.cadastrar(obs));
        return ResponseEntity.ok().body(obsCriada);
    }

    @PutMapping
    public ResponseEntity<ObservacaoDTO> atulizar (@Valid @RequestBody Observacao obs, int id){
        ObservacaoDTO obsAtualizada = ObservacaoMapper.toDTO(serviceObservacao.atulizar(id, obs));
        return obsAtualizada == null ? ResponseEntity.notFound().build():ResponseEntity.ok().body(obsAtualizada);
    }


}
