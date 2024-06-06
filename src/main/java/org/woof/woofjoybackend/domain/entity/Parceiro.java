package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParceiro;

    private Integer idUser;

    @PastOrPresent
    private LocalDate dataEntrada;

    @Pattern(regexp = "^(ambos|dogWalker|dogSitter)$", message = "O tipo de serviço deve ser 'Ambos', 'dogWalker' ou 'dogSitter'")
    private String tipoServico;

    @Max(value = 5)
    @Min(value = 0)
    @Column(columnDefinition = "DOUBLE DEFAULT 0.0")
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

    @BooleanFlag
    private Boolean premium;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "parceiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FichaServico> servicos;

    public Parceiro (Usuario usuario){
        this.dataEntrada = LocalDate.now();
        this.usuario = usuario;
        this.servicos = new ArrayList<>();
        this.aceitaDogEspecial = false;
        this.aceitaDogIdoso = false;
        this.aceitaDogBravo = false;
        this.aceitaDogGrande = false;
        this.aceitaDogCio = false;
        this.premium = false;
    }


}