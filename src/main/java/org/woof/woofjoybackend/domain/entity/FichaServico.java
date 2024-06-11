package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    @Pattern(regexp = "^(Dog Sitter|Dog Walker)$", message = "O tipo deve ser 'Dog Walker' ou 'Dog Sitter'")
    private String tipoServico;
    @Positive
    private Double valor;
    @ManyToOne
    @JoinColumn(name = "parceiro")
    private Parceiro parceiro;
    @OneToMany(mappedBy = "fkFichaServico")
    private List<Servico> servicos;
    private String relatorio;
    @OneToOne(mappedBy = "fkFichaServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Residencia residencia;

    public FichaServico() {
        this.servicos = new ArrayList<>();
        this.residencia = new Residencia();
    }
}
