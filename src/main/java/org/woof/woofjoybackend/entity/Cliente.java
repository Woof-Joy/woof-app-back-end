package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.woof.woofjoybackend.entity.object.Dog;

import java.util.List;

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

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> cachorros;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }
}