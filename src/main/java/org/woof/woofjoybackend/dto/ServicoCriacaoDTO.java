package org.woof.woofjoybackend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.woof.woofjoybackend.domain.FilaObj;
import org.woof.woofjoybackend.entity.Avaliacao;
import org.woof.woofjoybackend.entity.Dog;
import org.woof.woofjoybackend.entity.FichaServico;
import org.woof.woofjoybackend.entity.Relatorio;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ServicoCriacaoDTO {
    private Integer id;
    @PastOrPresent
    private LocalDateTime inicioDoServico;
    @Future
    private LocalDateTime fimDoServico;
    @NotNull
    @Pattern(regexp = "^(aguardandoConfirmacao|aguardandoInicio|emAndamento|concluido)$", message = "O tamanho deve ser 'aguardandoConfimacao', 'aguardandoInicio', 'emAndamento' ou 'concluido'")
    private String status;
    private Integer idParceiro;
    @Pattern(regexp = "^(dogSitter|dogWalker)$", message = "O tipo deve ser 'dogSitter' ou 'dogWalker'")
    private String tipoServico;

    private FilaObj<Integer> idCachorro;

    public ServicoCriacaoDTO() {
        this.status = "aguardandoConfirmacao";
    }




















    @NotNull
    private List<Integer> idCachorros;
}
