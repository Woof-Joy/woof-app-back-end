package org.woof.woofjoybackend.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Servico;
import org.woof.woofjoybackend.service.ServiceServico;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController {
    private final ServiceServico serviceServico;

    @PostMapping
    ResponseEntity<Servico> postServico(@Valid @RequestBody Servico servico){
        return ResponseEntity.created(null).body(serviceServico.post(servico));
    }

    @GetMapping("/{id}")
    ResponseEntity<Servico> getServicoById(@PathVariable Integer id){
        return ResponseEntity.ok(serviceServico.getById(id));
    }

    @GetMapping()
    ResponseEntity<List<Servico>> getAll(){
        List<Servico> lista = serviceServico.getAll();

        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id){
        serviceServico.delete(id);
        return ResponseEntity.ok().build();
    }
}
