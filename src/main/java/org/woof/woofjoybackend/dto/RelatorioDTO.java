package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RelatorioDTO {
    @Size(max = 1000, message = "O relatório deve ter no máximo 2000 caracteres.")
    private String relatorioTxt;

}
