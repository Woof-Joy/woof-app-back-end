package org.woof.woofjoybackend.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.RelatorioDTO;
import org.woof.woofjoybackend.dto.ServicoCriacaoDTO;
import org.woof.woofjoybackend.dto.mapper.ServicoMapper;
import org.woof.woofjoybackend.domain.entity.Servico;
import org.woof.woofjoybackend.service.ServiceServico;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicoController {
    private final ServiceServico serviceServico;

    @PostMapping
    ResponseEntity<ServicoCriacaoDTO> postServico(@Valid @RequestBody ServicoCriacaoDTO servico) {
        return ResponseEntity.created(null).body(ServicoMapper.toCriacaoDTO(serviceServico.post(servico)));
    }

    @PutMapping("/relatorio/{id-servico}")
    ResponseEntity<RelatorioDTO> postRelatorio(@Valid @RequestBody RelatorioDTO relatorio, @PathVariable("id-servico") Integer idServico) {

        return ResponseEntity.status(201).body(serviceServico.putRelatorio(relatorio.getRelatorioTxt(), idServico));
    }

    @PatchMapping("/{id}")
    ResponseEntity<ServicoCriacaoDTO> patchServico(@PathVariable Integer id) {
        return ResponseEntity.ok(ServicoMapper.toCriacaoDTO(serviceServico.patch(id)));
    }

    @GetMapping("/{id}")
    ResponseEntity<ServicoCriacaoDTO> getServicoById(@PathVariable Integer id) {
        return ResponseEntity.ok(ServicoMapper.toCriacaoDTO(serviceServico.getById(id)));
    }

    @GetMapping("/cliente/{id}")
    ResponseEntity<List<ServicoCriacaoDTO>> getServicoByClienteId(@PathVariable Integer id) {
        List<Servico> lista = serviceServico.getByIdCliente(id);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ServicoMapper.toCriacaoDTOList(lista));
    }

    @GetMapping()
    ResponseEntity<List<ServicoCriacaoDTO>> getAll() {
        List<Servico> lista = serviceServico.getAll();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ServicoMapper.toCriacaoDTOList(lista));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceServico.delete(id);
        return ResponseEntity.ok().build();
    }
}
