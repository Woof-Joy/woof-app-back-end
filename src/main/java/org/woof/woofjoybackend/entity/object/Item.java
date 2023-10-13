package org.woof.woofjoybackend.entity.object;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.woof.woofjoybackend.entity.Usuario;

@Setter
@AllArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String titulo;
    private String imgItemDoacao;
    @Size(max = 1000)
    private String descricao;
    private Boolean entrega;
    private Boolean marcaPontoEncontro;
    private Boolean enviaCorreio;
    private Boolean cobraTaxa;

    @ManyToOne
    @JoinColumn(name = "fkDono")
    private Usuario dono;
    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getImgItemDoacao() {
        return imgItemDoacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getEntrega() {
        return entrega;
    }

    public Boolean getMarcaPontoEncontro() {
        return marcaPontoEncontro;
    }

    public Boolean getEnviaCorreio() {
        return enviaCorreio;
    }

    public Boolean getCobraTaxa() {
        return cobraTaxa;
    }
}
