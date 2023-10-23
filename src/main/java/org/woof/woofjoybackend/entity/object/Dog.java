package org.woof.woofjoybackend.entity.object;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Observacao;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "cachorro")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @PastOrPresent
    private LocalDate dtNasc;
    @Size(max = 200)
    private String imgCachorro;

    @BooleanFlag
    private Boolean rga;

    @NotBlank
    @Size(max = 50)
    private Double peso;

    @NotBlank
    @Size(max = 70)
    private String raca;

    @NotNull
    @Pattern(regexp = "^(pequeno|médio|grande)$", message = "O tamanho deve ser 'pequeno', 'médio' ou 'grande'")
    private String porte;

    @BooleanFlag
    private Boolean convenio;

    @Size(max = 50)
    private String carteirinha;

    @Pattern(regexp = "^[MF]$", message = "O gênero deve ser 'M' ou 'F'")
    private char genero;

    @Null
    @Min(0)
    @Max(5)
    private Integer agressivo;

    @BooleanFlag
    private Boolean deficiencia;

    @ManyToOne
    @JoinColumn(name = "fkDono")
    private Cliente fkDono;

    @OneToMany(mappedBy = "cachorro", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Observacao> observacaoList;


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

    public Integer getAgressivo() {
        return agressivo;
    }

    public void setAgressivo(Integer agressivo) {
        this.agressivo = agressivo;
    }

    public Boolean getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(Boolean deficiencia) {
        this.deficiencia = deficiencia;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Cliente getFkDono() {
        return fkDono;
    }

    public void setFkDono(Cliente fkDono) {
        this.fkDono = fkDono;
    }

    public List<Observacao> getObservacaoList() {
        return observacaoList;
    }

    public void setObservacaoList(List<Observacao> observacaoList) {
        this.observacaoList = observacaoList;
    }
}
