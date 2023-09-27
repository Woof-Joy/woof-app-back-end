package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
public class Parceiro {

    @Id
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

    public Parceiro(Usuario usuario) {
        this.usuario = usuario;
        this.idParceiro = usuario.getId();
        this.dataEntrada = LocalDate.now();
    }

    public Parceiro() {
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Integer getMaxDogs() {
        return maxDogs;
    }

    public void setMaxDogs(Integer maxDogs) {
        this.maxDogs = maxDogs;
    }

    public Boolean getAceitaDogEspecial() {
        return aceitaDogEspecial;
    }

    public void setAceitaDogEspecial(Boolean aceitaDogEspecial) {
        this.aceitaDogEspecial = aceitaDogEspecial;
    }

    public Boolean getAceitaDogIdoso() {
        return aceitaDogIdoso;
    }

    public void setAceitaDogIdoso(Boolean aceitaDogIdoso) {
        this.aceitaDogIdoso = aceitaDogIdoso;
    }

    public Boolean getAceitaDogBravo() {
        return aceitaDogBravo;
    }

    public void setAceitaDogBravo(Boolean aceitaDogBravo) {
        this.aceitaDogBravo = aceitaDogBravo;
    }

    public Boolean getAceitaDogGrande() {
        return aceitaDogGrande;
    }

    public void setAceitaDogGrande(Boolean aceitaDogGrande) {
        this.aceitaDogGrande = aceitaDogGrande;
    }

    public Boolean getAceitaDogaCio() {
        return aceitaDogaCio;
    }

    public void setAceitaDogaCio(Boolean aceitaDogaCio) {
        this.aceitaDogaCio = aceitaDogaCio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setIdParceiro(Integer idParceiro) {
        this.idParceiro = idParceiro;
    }
}
