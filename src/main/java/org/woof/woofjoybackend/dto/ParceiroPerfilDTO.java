package org.woof.woofjoybackend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ParceiroPerfilDTO {
    private Integer idUser;
    private String nome;
    private LocalDate dataEntrada;
    private List<FichaServicoParceiroDTO> fichas;
}