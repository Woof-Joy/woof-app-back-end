package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FichaServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "^(ambos|walker|sitter)$", message = "O tipo deve ser 'ambos', 'walker' ou 'sitter'")
    private String tipoServico;
    @Positive
    private Double valor;
    @ManyToOne
    @JoinColumn(name = "parceiro")
    private Parceiro parceiro;
    @OneToMany(mappedBy = "fkFichaServico")
    private List<Servico> servicos;

}
