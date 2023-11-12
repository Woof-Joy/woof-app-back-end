package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Parceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idParceiro;

    @PastOrPresent
    private LocalDate dataEntrada;

    @Pattern(regexp = "^(ambos|dogWalker|dogSitter)$", message = "O tipo de serviõ deve ser 'Ambos', 'dogWalker' ou 'dogSitter'")
    private String tipoServico;

    @Max(value = 5)
    @Min(value = 0)
    private Double estrelas;

    @DecimalMin(value = "1")
    private Integer maxDogs;

    @BooleanFlag
    private Boolean aceitaDogEspecial;

    @BooleanFlag
    private Boolean aceitaDogIdoso;

    @BooleanFlag
    private Boolean aceitaDogBravo;

    @BooleanFlag
    private Boolean aceitaDogGrande;

    @BooleanFlag
    private Boolean aceitaDogCio;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "parceiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servico> servicos;

    public Parceiro (Usuario usuario){
        this.usuario = usuario;
    }


}