package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.dto.ParceiroDTO;
import org.woof.woofjoybackend.service.ServiceParceiro;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parceiros")
public class ParceiroController {
    private final ServiceParceiro serviceParceiro;
    private final ServiceUser serviceUser;

    @Autowired
    public ParceiroController(ServiceParceiro serviceParceiro, ServiceUser serviceUser) {
        this.serviceParceiro = serviceParceiro;
        this.serviceUser = serviceUser;
    }

    @GetMapping
    public ResponseEntity<List<ParceiroDTO>> getParceiros() {
        List<Parceiro> listaParceiros = serviceParceiro.getParceiros();

        if (!listaParceiros.isEmpty()) {
            List<ParceiroDTO> listParceirosDTO = new ArrayList<>();
            for (Parceiro p:
                 listaParceiros) {
                listParceirosDTO.add(new ParceiroDTO(p.getIdParceiro(), p.getDataEntrada(), p.getMaxDogs(), p.getAceitaDogEspecial(), p.getAceitaDogIdoso(), p.getAceitaDogBravo(), p.getAceitaDogGrande(), p.getAceitaDogCio(), p.getUsuario().getNome(), p.getUsuario().getSobrenome(), p.getUsuario().getCpf(), p.getUsuario().getCep(), p.getUsuario().getNumero(), p.getUsuario().getEmail(), p.getUsuario().getDataNasc(), p.getUsuario().getDescricao(), p.getEstrelas()));
            }
            return ResponseEntity.status(200).body(listParceirosDTO);
        }
        return ResponseEntity.status(204).build();
    }

       @GetMapping("/{id}")
    public ResponseEntity<Parceiro> getParceiroById(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceParceiro.getParceirosById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parceiro> putParceiro(@Valid @RequestBody Parceiro parceiro, @PathVariable Integer id) {
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
