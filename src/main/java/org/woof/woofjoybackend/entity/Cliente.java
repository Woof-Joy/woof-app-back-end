package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Setter;



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

    public Cliente() {
    }

    public Integer getIdCliente() {
        return idCliente;
    }

}
