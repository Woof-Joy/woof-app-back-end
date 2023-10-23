package org.woof.woofjoybackend.entity.object;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.woof.woofjoybackend.entity.Cliente;

import java.time.LocalDate;

//    MUDO AQUI

@Entity
@Table(name = "cachorro")
public class Dog {
    @Id
    private Integer id;

    private String nome;

    private LocalDate dtNasc;

    private String imgCachorro;

    private Boolean rga;

    private Double peso;

    private String porte;

    private Boolean convenio;

    private String carteirinha;

    private char genero;

    private String agrecividade;

    private String deficiencia;

    @ManyToOne
    private Cliente dono;


    public Dog() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getImgCachorro() {
        return imgCachorro;
    }

    public void setImgCachorro(String imgCachorro) {
        this.imgCachorro = imgCachorro;
    }

    public Boolean getRga() {
        return rga;
    }

    public void setRga(Boolean rga) {
        this.rga = rga;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public Boolean getConvenio() {
        return convenio;
    }

    public void setConvenio(Boolean convenio) {
        this.convenio = convenio;
    }

    public String getCarteirinha() {
        return carteirinha;
    }

    public void setCarteirinha(String carteirinha) {
        this.carteirinha = carteirinha;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getAgrecividade() {
        return agrecividade;
    }

    public void setAgrecividade(String agrecividade) {
        this.agrecividade = agrecividade;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }
}
