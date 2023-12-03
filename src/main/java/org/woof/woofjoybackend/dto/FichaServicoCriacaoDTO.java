package org.woof.woofjoybackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class FichaServicoCriacaoDTO {
    private Integer idParceiro;
    private String tipoServico;
    private Double valor;
    private List<ServicoDTO> servicos;
    private Integer qtdServico;
}
