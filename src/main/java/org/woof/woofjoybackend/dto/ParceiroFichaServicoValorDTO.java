package org.woof.woofjoybackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParceiroFichaServicoValorDTO {
    private Integer idParceiro;
    private String tipoServico;
    private Double valor;
}