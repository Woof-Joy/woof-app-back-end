package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ObservacaoDTO {
    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^(alergia|doença|medicação)$", message = "O tipo deve ser 'alergia', 'doença' ou 'medicação'")
    private String tipo;

}
