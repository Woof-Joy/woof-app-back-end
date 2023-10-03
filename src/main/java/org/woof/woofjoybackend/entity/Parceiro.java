package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
