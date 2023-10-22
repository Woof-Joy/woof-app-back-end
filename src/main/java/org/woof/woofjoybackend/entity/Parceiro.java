package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.woof.woofjoybackend.entity.object.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
public class Parceiro {
//    MUDO AQUI

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    Integer idParceiro;

    @PastOrPresent
    @Getter @Setter
    private LocalDate dataEntrada;

    @Getter @Setter
    private Integer maxDogs;

    @Getter @Setter
    private Boolean aceitaDogEspecial;

    @Getter @Setter
    private Boolean aceitaDogIdoso;

    @Getter @Setter
    private Boolean aceitaDogBravo;

    @Getter @Setter
    private Boolean aceitaDogGrande;

    @Getter @Setter
    private Boolean aceitaDogaCio;

    @OneToOne
    @JoinColumn(name = "fkUsuario")
    private Usuario usuario;

    public Parceiro(Usuario usuario) {
        this.usuario = usuario;
    }

    public Parceiro() {

    }

    public Integer getIdParceiro() {
        return idParceiro;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public Integer getMaxDogs() {
        return maxDogs;
    }

    public Boolean getAceitaDogEspecial() {
        return aceitaDogEspecial;
    }

    public Boolean getAceitaDogIdoso() {
        return aceitaDogIdoso;
    }

    public Boolean getAceitaDogBravo() {
        return aceitaDogBravo;
    }

    public Boolean getAceitaDogGrande() {
        return aceitaDogGrande;
    }

    public Boolean getAceitaDogaCio() {
        return aceitaDogaCio;
    }

}
