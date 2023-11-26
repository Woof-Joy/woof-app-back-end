package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvaliacaoPrestadorDTO {
    @NotNull
    @Max(value = 5)
    @Min(value = 0)
    private Integer nota;

    private String comentario;

    private String tipoServico;

    private ClienteAvaliacaoDTO cliente;
}
