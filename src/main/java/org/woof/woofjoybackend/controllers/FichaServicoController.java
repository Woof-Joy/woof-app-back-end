package org.woof.woofjoybackend.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.FichaServicoCriacaoDTO;
import org.woof.woofjoybackend.dto.ParceiroFichaServicoDTO;
import org.woof.woofjoybackend.dto.ParceiroFichaServicoValorDTO;
import org.woof.woofjoybackend.dto.mapper.FichaServicoMapper;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.dto.mapper.ServicoFichaMapper;
import org.woof.woofjoybackend.service.ServiceFichaServico;

import java.util.List;

@RestController
@RequestMapping("/api/ficha")
@RequiredArgsConstructor
public class FichaServicoController {

    private final ServiceFichaServico service;
    public static final ServicoFichaMapper INSTANCE = Mappers.getMapper(ServicoFichaMapper.class);


    @PostMapping()
    ResponseEntity<ParceiroFichaServicoDTO> postFicha(@Valid @RequestBody FichaServicoCriacaoDTO ficha) {
        return ResponseEntity.created(null).body(FichaServicoMapper.toDTOServico(service.postFicha(ficha)));
    }

    @GetMapping("parceiro/{idParceiro}")
    ResponseEntity<List<ParceiroFichaServicoDTO>> findByParceiro(@PathVariable Integer idParceiro) {
        List<FichaServico> lista = service.findByParceiro(idParceiro);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(INSTANCE.fichaServicosToParceiroFichaServicoDTOs(lista));
    }

    @PutMapping()
    ResponseEntity<ParceiroFichaServicoDTO> putFicha(@Valid @RequestBody ParceiroFichaServicoValorDTO ficha) {
        return ResponseEntity.created(null).body(FichaServicoMapper.toDTOServico(service.putFicha(ficha)));
    }

}