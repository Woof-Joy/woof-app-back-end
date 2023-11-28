package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @PastOrPresent
    private LocalDateTime inicioDoServico;
    @Future
    private LocalDateTime fimDoServico;

    @NotNull
    @Pattern(regexp = "^(aguardandoConfirmacao|aguardandoInicio|emAndamento|concluido)$", message = "O tamanho deve ser 'aguardandoConfimacao', 'aguardandoInicio', 'emAndamento' ou 'concluido'")
    private String status;

    @OneToOne(mappedBy = "fkServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Relatorio relatorio;

    @OneToOne(mappedBy = "fkServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Avaliacao avaliacao;

    @ManyToOne
    @JoinColumn(name = "fkFichaServico")
    private FichaServico fkFichaServico;

    @ManyToMany
    private List<Dog> cachorros;

    public Servico() {
        this.status = "aguardandoConfirmacao";
    }
}
