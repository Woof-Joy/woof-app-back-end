package org.woof.woofjoybackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TipoServico {
    @Id
    private Integer id;

    @OneToMany(mappedBy = "tipoServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servico> servicos;
}
