package org.woof.woofjoybackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
public class FichaServicoParceiroDTO {
    private String tipoServico;
    private Double valor;
    private List<ServicoAvaliacaoDTO> servicos;
}
