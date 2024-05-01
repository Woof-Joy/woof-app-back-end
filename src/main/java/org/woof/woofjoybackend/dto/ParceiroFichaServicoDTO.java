package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ParceiroFichaServicoDTO {
    private Integer id;
    @Pattern(regexp = "^(ambos|walker|sitter)$", message = "O tipo deve ser 'ambos', 'walker' ou 'sitter'")
    private String tipoServico;
    @Positive
    private Double valor;
    private Integer qtdServico;
    private List<ServicoFichaDTO> servicos;
}