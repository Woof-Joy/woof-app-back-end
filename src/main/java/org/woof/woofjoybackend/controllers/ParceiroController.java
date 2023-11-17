package org.woof.woofjoybackend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.ParceiroAvaliacaoFeedDTO;
import org.woof.woofjoybackend.dto.mapper.ParceiroMapper;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.dto.ParceiroDTO;
import org.woof.woofjoybackend.service.ServiceParceiro;
import org.woof.woofjoybackend.service.ServiceUser;
import org.woof.woofjoybackend.service.gateway.ServiceCEP;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parceiros")
public class ParceiroController {
    private final ServiceParceiro serviceParceiro;
    private final ServiceUser serviceUser;

    private final ServiceCEP serviceCEP;

    @Autowired
    public ParceiroController(ServiceParceiro serviceParceiro, ServiceUser serviceUser, ServiceCEP serviceCEP) {
        this.serviceParceiro = serviceParceiro;
        this.serviceUser = serviceUser;
        this.serviceCEP = serviceCEP;
    }


    @GetMapping
    public ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> listagemParceiros() {
        List<Parceiro> listaParceiros = serviceParceiro.listaParceiros();
        if (!listaParceiros.isEmpty()) {
            List<ParceiroAvaliacaoFeedDTO> listParceirosDTO = ParceiroMapper.toDTOAvaliacao(listaParceiros);
            return ResponseEntity.status(200).body(listParceirosDTO);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/avaliacao/asc")
    public ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> listagemParceirosOrdenadosPorAvaliacao(@RequestParam Integer id) {
        List<Parceiro> listaParceiros = serviceParceiro.listarParceiroOrdenadoPorAvaliacaoAsc(id);
        if (!listaParceiros.isEmpty()) {
            List<ParceiroAvaliacaoFeedDTO> listParceirosDTO = ParceiroMapper.toDTOAvaliacao(listaParceiros);
            return ResponseEntity.status(200).body(listParceirosDTO);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/avaliacao/desc")
    public ResponseEntity<List<ParceiroAvaliacaoFeedDTO>> listagemParceirosOrdenadosPorAvaliacaoDesc(@RequestParam Integer id) {
        List<Parceiro> listaParceiros = serviceParceiro.listarParceiroOrdenadoPorAvaliacaoDesc(id);
        if (!listaParceiros.isEmpty()) {
            List<ParceiroAvaliacaoFeedDTO> listParceirosDTO = ParceiroMapper.toDTOAvaliacao(listaParceiros);
            return ResponseEntity.status(200).body(listParceirosDTO);
        }
        return ResponseEntity.status(204).build();
    }





    @GetMapping("/{id}")
    public ResponseEntity<Parceiro> listaParceiroPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceParceiro.listarParceiroPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parceiro> attParceiro(@Valid @RequestBody Parceiro parceiro, @PathVariable Integer id) {
        if (serviceParceiro.idExiste(id)) {
            return ResponseEntity.status(200).body(serviceParceiro.attParceiro(parceiro, id));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParceiro(@PathVariable Integer id) {
        if (serviceParceiro.idExiste(id)) {
            serviceParceiro.deleteParceiro(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    


}
