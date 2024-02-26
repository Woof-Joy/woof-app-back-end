package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    private Integer idUser;

    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "fkDono", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> dogList;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
        this.idUser = usuario.getId();// ajuste necessário para melhor intercambiação na aplicação front-end
        this.dogList = new ArrayList<>();
    }
}