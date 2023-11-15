package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Servico {
    @Id
    private Integer id;
    private LocalDate inicioDoServico;
    private LocalDate fimDoServico;
    private String status;
    private String tipoServico;

    @ManyToOne
    private Parceiro parceiro;

    @ManyToMany
    private List<Dog> cachorros;

    @OneToOne
    private Avaliacao avaliacao;
}
