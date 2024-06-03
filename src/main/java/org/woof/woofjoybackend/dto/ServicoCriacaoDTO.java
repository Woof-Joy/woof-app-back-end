package org.woof.woofjoybackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.woof.woofjoybackend.domain.FilaObj;

import java.time.LocalDateTime;
import java.util.List;

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
    @Pattern(regexp = "^(Dog Sitter|Dog Walker)$", message = "O tipo deve ser 'Dog Sitter' ou 'Dog Walker'")
    private String tipoServico;
    private String relatoório = "";
    private Integer idCliente;
    public ServicoCriacaoDTO() {
        this.status = "Aguardando Confirmação";
    }
}
