package org.woof.woofjoybackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.ParceiroAvaliacaoFeedDTO;
import org.woof.woofjoybackend.dto.mapper.ParceiroMapper;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.service.ServiceParceiro;
import org.woof.woofjoybackend.service.ServiceUser;
import org.woof.woofjoybackend.service.gateway.ServiceCEP;

import java.util.List;

@RestController
@RequestMapping("/parceiros")
@RequiredArgsConstructor
public class ParceiroController {
    private final ServiceParceiro serviceParceiro;

    @GetMapping("/{id}")
    public ResponseEntity<Parceiro> listaParceiroPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceParceiro.getParceiroById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parceiro> attParceiro(@Valid @RequestBody Parceiro parceiro, @PathVariable Integer id) {
        if (serviceParceiro.idExiste(id)) {
            return ResponseEntity.status(200).body(serviceParceiro.putParceiro(parceiro, id));
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
