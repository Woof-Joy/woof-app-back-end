package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.woof.woofjoybackend.domain.PilhaObj;

import java.util.List;

@Data
public class ParceiroFichaServicoDTO {
    private Integer id;

    @Pattern(regexp = "^(ambos|walker|sitter)$", message = "O tipo deve ser 'ambos', 'walker' ou 'sitter'")
    private String tipoServico;

    @Positive
    private Double valor;

    private PilhaObj<ServicoDTO> servico;

    private Integer qtdServico;

    public Integer getQtdServico() {
        return servicos.size();
    }







    private List<ServicoDTO> servicos;

}
