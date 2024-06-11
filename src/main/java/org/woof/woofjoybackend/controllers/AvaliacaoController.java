package org.woof.woofjoybackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.AvaliacaoPrestadorDTO;
import org.woof.woofjoybackend.dto.mapper.AvaliacaoParceiroMapper;
import org.woof.woofjoybackend.domain.entity.Avaliacao;
import org.woof.woofjoybackend.service.ServiceAvaliacao;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    private final ServiceAvaliacao serviceAvaliacao;

    @GetMapping("/parceiro/{id}")
    ResponseEntity<List<Avaliacao>> getAvaliacaoByParceiro(@PathVariable Integer id) {
        List<Avaliacao> lista = serviceAvaliacao.getAvaliacaoByIdParceiro(id);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/{id-parceiro}")
    ResponseEntity<Avaliacao> postAvaliacao(
            @PathVariable("id-parceiro") Integer idParceiro,
            @RequestBody Avaliacao avaliacao
    ) {
       return ResponseEntity.created(null).body(serviceAvaliacao.postAvaliacao(avaliacao, idParceiro));
    }


}
