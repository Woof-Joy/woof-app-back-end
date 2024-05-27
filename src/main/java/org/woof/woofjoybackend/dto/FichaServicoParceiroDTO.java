package org.woof.woofjoybackend.dto;

import jdk.jfr.BooleanFlag;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
public class FichaServicoParceiroDTO {
    private String tipoServico;
    private Double valor;
    private List<ServicoAvaliacaoDTO> servicos;
    private Boolean areaExterna;
    private Boolean temAnimais;
    private Boolean temCriancas;
    private Boolean rotaFuga;
    private Boolean dogSofaCama;
}
