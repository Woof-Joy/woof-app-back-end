package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@AllArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCliente;

    @OneToOne
    @JoinColumn(name = "fkUsuario")
    private Usuario usuario;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }


    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> dogList;

    public Cliente() {
        this.dogList = new ArrayList<>();
    }

    public Integer getIdCliente() {
        return idCliente;
    }

}