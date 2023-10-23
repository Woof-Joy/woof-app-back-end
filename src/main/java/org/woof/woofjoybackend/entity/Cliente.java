package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCliente;
//    MUDO AQUI

    @OneToOne
    private Usuario usuario;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }
}
