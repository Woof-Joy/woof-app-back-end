package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Positive
    @DecimalMax(value = "5.0")
    private Integer nota;
    private String comentario;
    @OneToOne
    @JoinColumn(name = "fkServico")
    private Servico fkServico;
}
