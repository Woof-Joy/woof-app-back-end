package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Relatorio {
    @Id
    private Integer id;

    @Size(max = 255)
    private String conteudo;

    @OneToOne
    @JoinColumn(name = "fkServico")
    private Servico fkServico;
}
