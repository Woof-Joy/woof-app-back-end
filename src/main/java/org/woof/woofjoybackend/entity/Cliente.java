package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "fkDono", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> dogList;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }
}