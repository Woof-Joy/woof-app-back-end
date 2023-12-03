package org.woof.woofjoybackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.ParceiroAvaliacaoFeedDTO;
import org.woof.woofjoybackend.dto.mapper.ParceiroMapper;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.dto.ParceiroDTO;
import org.woof.woofjoybackend.service.ServiceParceiro;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed-parceiro")
public class FeedParceiroController {

    private final ServiceParceiro serviceParceiro;

    @GetMapping()
    public ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> getParceiros() {
        return retornaListaAvaliacao(serviceParceiro.getParceiros());
    }

    @GetMapping("/{tipo}")
    public ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> getParceirosByTipoServico(@PathVariable String tipo) {
        return retornaListaAvaliacao(serviceParceiro.getParceirosByTipoServico(tipo));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> getParceirosByNome(@PathVariable String nome) {
        return retornaListaAvaliacao(serviceParceiro.getParceirosByNome(nome));
    }

    @GetMapping("/ordenacao")
    ResponseEntity<List<ParceiroDTO>> ordenaFeed(@RequestParam String ordenacao, @RequestBody List<ParceiroDTO> feed) {
        if (!feed.isEmpty()) {
            return ResponseEntity.ok(serviceParceiro.ordenaFeed(ordenacao, feed));
        }
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> retornaListaAvaliacao(List<Parceiro> listaParceiros) {
        if (!listaParceiros.isEmpty()) {
            return ResponseEntity.status(200).body(ParceiroMapper.toDTOAvaliacao(listaParceiros));
        }
        return ResponseEntity.noContent().build();
    }
}
