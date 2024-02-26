package org.woof.woofjoybackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.ParceiroDTO;
import org.woof.woofjoybackend.dto.mapper.ParceiroMapper;
import org.woof.woofjoybackend.domain.entity.Parceiro;
import org.woof.woofjoybackend.service.users.ServiceParceiro;
import org.woof.woofjoybackend.service.users.ServiceUser;
import org.woof.woofjoybackend.service.client.ServiceCEP;
import java.util.List;

@RestController
@RequestMapping("/parceiros")
@RequiredArgsConstructor
public class ParceiroController {
    private final ServiceParceiro serviceParceiro;

    private final ServiceUser serviceUser;

    private final ServiceCEP serviceCEP;


    @GetMapping()
    public ResponseEntity<List<ParceiroDTO>> listagemParceiros() {
        List<Parceiro> listaParceiros = serviceParceiro.getParceiros();
        if (!listaParceiros.isEmpty()) {
            List<ParceiroDTO> listParceirosDTO = ParceiroMapper.toDTO(listaParceiros);
            return ResponseEntity.status(200).body(listParceirosDTO);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParceiroDTO> listaParceiroPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ParceiroMapper.toDTO(serviceParceiro.findById(id)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ParceiroDTO> attParceiro(@Valid @RequestBody Parceiro parceiro, @PathVariable Integer id) {
        if (serviceParceiro.idExiste(id)) {
            return ResponseEntity.status(200).body(ParceiroMapper.toDTO(serviceParceiro.putParceiro(parceiro, id)));
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
