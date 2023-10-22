package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Parceiro {
//    MUDO AQUI

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idParceiro;

    @PastOrPresent
    private LocalDate dataEntrada;
    private Integer maxDogs;
    private Boolean aceitaDogEspecial;
    private Boolean aceitaDogIdoso;
    private Boolean aceitaDogBravo;
    private Boolean aceitaDogGrande;
    private Boolean aceitaDogaCio;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Parceiro() {
    }
}
