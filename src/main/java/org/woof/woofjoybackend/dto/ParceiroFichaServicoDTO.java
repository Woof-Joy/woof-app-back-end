package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.woof.woofjoybackend.entity.Servico;

import java.util.List;

@Data
public class ParceiroFichaServicoDTO {
    @Pattern(regexp = "^(ambos|walker|sitter)$", message = "O tipo deve ser 'ambos', 'walker' ou 'sitter'")
    private String tipoServico;

    @Positive
    private Double valor;

    private List<ServicoDTO> servicos;
}
