package org.woof.woofjoybackend.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class ServicoAvaliacaoDTO {
    private String fotoParceiro;
    private String nomeParceiro;
    private Integer nota;
    private String comentario;
}
