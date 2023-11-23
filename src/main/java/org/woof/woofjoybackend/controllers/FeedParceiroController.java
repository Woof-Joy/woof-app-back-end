package org.woof.woofjoybackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.ParceiroAvaliacaoFeedDTO;
import org.woof.woofjoybackend.dto.mapper.ParceiroMapper;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.dto.ParceiroDTO;
import org.woof.woofjoybackend.service.ServiceParceiro;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed-parceiro")
public class FeedParceiroController {

    private final ServiceParceiro serviceParceiro;

    @GetMapping()
    public ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> getParceirosByTipoServico(@RequestParam String tipo){
        List<Parceiro> listaParceiros = serviceParceiro.getParceirosByTipoServico(tipo);

        if (!listaParceiros.isEmpty()){
            List<ParceiroAvaliacaoFeedDTO> listParceirosDTO = new ArrayList<>();
            for (Parceiro p:
                    listaParceiros) {
                listParceirosDTO.add(ParceiroMapper.toDTOAvaliacao(p));
            }
            return ResponseEntity.status(200).body(listParceirosDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> getParceirosByNome(@PathVariable String nome){
        List<Parceiro> listaParceiros = serviceParceiro.getParceirosByNome(nome);

        if (!listaParceiros.isEmpty()){
            List<ParceiroAvaliacaoFeedDTO> listParceirosDTO = new ArrayList<>();
            for (Parceiro p:
                    listaParceiros) {
                listParceirosDTO.add(ParceiroMapper.toDTOAvaliacao(p));
            }
            return ResponseEntity.status(200).body(listParceirosDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ordenacao")
    ResponseEntity<List<ParceiroDTO>> ordenaFeed(@RequestParam String ordenacao, @RequestBody List<ParceiroDTO> feed){
        if(!feed.isEmpty()){
            return ResponseEntity.ok(serviceParceiro.ordenaFeed(ordenacao, feed));
        }
        return ResponseEntity.noContent().build();
    }
}
