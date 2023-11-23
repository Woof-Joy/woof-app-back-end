package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @FutureOrPresent
    private LocalDate inicioDoServico;
    @Future
    private LocalDate fimDoServico;

    @NotNull
    @Pattern(regexp = "^(aguardandoConfimacao|aguardandoInicio|emAndamento|concluido)$", message = "O tamanho deve ser 'aguardandoConfimacao', 'aguardandoInicio', 'emAndamento' ou 'concluido'")
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
}
