package org.woof.woofjoybackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParceiroFichaServicoDTO {
    private Integer id;
    private String tipoServico;
    private Double valor;
    private String relatorio;
    private Integer qtdServico;
    private List<ServicoFichaDTO> servicos;
}