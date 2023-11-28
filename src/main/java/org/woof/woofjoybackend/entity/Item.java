package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.entity.Usuario;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String titulo;
    private String imgItemDoacao;
    private String categoria;
    private String estado;
    @Size(max = 1000)
    private String descricao;
    private Boolean entrega;
    private Boolean marcaPontoEncontro;
    private Boolean enviaCorreio;
    private Boolean cobraTaxa;
    private Boolean necessarioRetirada;

    @ManyToOne
    @JoinColumn(name = "fkDono")
    private Usuario dono;

    @OneToOne
    private DonoImagem donoImagem;

}
