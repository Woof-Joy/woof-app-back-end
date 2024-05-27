package org.woof.woofjoybackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    ResponseEntity<List<AvaliacaoPrestadorDTO>> getAvaliacaoByParceiro(@PathVariable Integer id) {
        List<Avaliacao> lista = serviceAvaliacao.getAvaliacaoByIdParceiro(id);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(AvaliacaoParceiroMapper.toDtoList(lista));
    }
}
