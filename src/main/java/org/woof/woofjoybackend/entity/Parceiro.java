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
@AllArgsConstructor
@Entity
public class Parceiro {

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
