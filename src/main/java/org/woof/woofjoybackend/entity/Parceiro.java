package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@Entity
public class Parceiro {

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
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Parceiro() {
    }
    public Parceiro(Usuario usuario) {
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }
}
