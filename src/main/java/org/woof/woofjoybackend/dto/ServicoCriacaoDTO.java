package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServicoCriacaoDTO {
    private Integer id;
    @FutureOrPresent
    private LocalDateTime inicioDoServico;
    @Future
    private LocalDateTime fimDoServico;
    @NotNull
    @Pattern(regexp = "^(Aguardando Confirmação|Em andamento|Concluído)$", message = "O tamanho deve ser 'Aguardando Confirmação', 'Em andamento' ' ou 'Concluído'")
    private String status;
    private Integer idParceiro;
    private String nomeParceiro;
    @Pattern(regexp = "^(Dog Sitter|Dog Walker)$", message = "O tipo deve ser 'Dog Sitter' ou 'Dog Walker'")
    private String tipoServico;
    @Size(max = 1000, message = "O relatório deve ter no máximo 2000 caracteres.")
    private String relatorio = "";
    private Integer idCliente;
    public ServicoCriacaoDTO() {
        this.status = "Aguardando Confirmação";
    }
}
