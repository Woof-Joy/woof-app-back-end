package org.woof.woofjoybackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.entity.dto.ParceiroDTO;
import org.woof.woofjoybackend.service.ServiceParceiro;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed-parceiro")
public class FeedParceiroController {

    private final ServiceParceiro serviceParceiro;

    @GetMapping()
    public ResponseEntity<List<ParceiroDTO>> getParceirosByTipoServico(@RequestParam String tipo){
        List<Parceiro> listaParceiros = serviceParceiro.getParceirosByTipoServico(tipo);

        if (!listaParceiros.isEmpty()){
            List<ParceiroDTO> listParceirosDTO = new ArrayList<>();
            for (Parceiro p:
                    listaParceiros) {
                listParceirosDTO.add(new ParceiroDTO(p.getIdParceiro(), p.getDataEntrada(), p.getMaxDogs(), p.getAceitaDogEspecial(), p.getAceitaDogIdoso(), p.getAceitaDogBravo(), p.getAceitaDogGrande(), p.getAceitaDogCio(), p.getUsuario().getNome(), p.getUsuario().getSobrenome(), p.getUsuario().getCpf(), p.getUsuario().getCep(), p.getUsuario().getNumero(), p.getUsuario().getEmail(), p.getUsuario().getDataNasc(), p.getUsuario().getDescricao(), p.getEstrelas()));
            }
            return ResponseEntity.status(200).body(listParceirosDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ParceiroDTO>> getParceirosByNome(@PathVariable String nome){
        List<Parceiro> listaParceiros = serviceParceiro.getParceirosByNome(nome);

        if (!listaParceiros.isEmpty()){
            List<ParceiroDTO> listParceirosDTO = new ArrayList<>();
            for (Parceiro p:
                    listaParceiros) {
                listParceirosDTO.add(new ParceiroDTO(p.getIdParceiro(), p.getDataEntrada(), p.getMaxDogs(), p.getAceitaDogEspecial(), p.getAceitaDogIdoso(), p.getAceitaDogBravo(), p.getAceitaDogGrande(), p.getAceitaDogCio(), p.getUsuario().getNome(), p.getUsuario().getSobrenome(), p.getUsuario().getCpf(), p.getUsuario().getCep(), p.getUsuario().getNumero(), p.getUsuario().getEmail(), p.getUsuario().getDataNasc(), p.getUsuario().getDescricao(), p.getEstrelas()));
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
