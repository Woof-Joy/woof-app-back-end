package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class FichaServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "^(dogSitter|dogWalker)$", message = "O tipo deve ser 'walker' ou 'sitter'")
    private String tipoServico;
    @Positive
    private Double valor;
    @ManyToOne
    @JoinColumn(name = "parceiro")
    private Parceiro parceiro;
    @OneToMany(mappedBy = "fkFichaServico")
    private List<Servico> servicos;

    public FichaServico() {
        this.servicos = new ArrayList<>();
    }
}
