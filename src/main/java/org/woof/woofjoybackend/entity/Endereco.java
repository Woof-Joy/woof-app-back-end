package org.woof.woofjoybackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.method.P;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {
    @Id
    private String cep;
    private String    logradouro;
    private String    complemento;
    private String    bairro;
    private String    localidade;
    private String    uf;
    @OneToOne
    @JoinColumn(name = "fkParceiro")
    private Usuario fkParceiro;
}
