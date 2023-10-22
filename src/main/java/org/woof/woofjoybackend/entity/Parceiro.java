package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    public Parceiro (Usuario usuario){
        this.usuario = usuario;
    }

}