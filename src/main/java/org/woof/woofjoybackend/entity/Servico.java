package org.woof.woofjoybackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Servico {
    @Id
    private Integer id;
    private LocalDate inicioDoServico;
    private LocalDate fimDoServico;
    private String status;

    @ManyToOne
    private Parceiro parceiro;
    @OneToOne
    private TipoServico tipoServico;
}
