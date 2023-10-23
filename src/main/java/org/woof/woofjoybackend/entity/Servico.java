package org.woof.woofjoybackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Servico {
    @Id
    private Integer id;
    private LocalDate inicioDoServico;
    private LocalDate fimDoServico;
    private String status;

    @ManyToOne
    private Parceiro parceiro;

    @ManyToOne
    private TipoServico tipoServico;
}
