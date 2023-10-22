package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCliente;

    @OneToOne
    private Usuario usuario;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }
}