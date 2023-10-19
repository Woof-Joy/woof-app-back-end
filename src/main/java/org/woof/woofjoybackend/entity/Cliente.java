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
    @JoinColumn(name = "fkUsuario")
    private Usuario usuario;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }


//    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Dog> dogList;
//
//    public Cliente() {
//        this.dogList = new ArrayList<>();
//    }

    public Integer getIdCliente() {
        return idCliente;
    }

}
