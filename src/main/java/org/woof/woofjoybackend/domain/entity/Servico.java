package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime inicioDoServico;
    private LocalDateTime fimDoServico;
    private String status;
    @OneToOne(mappedBy = "fkServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Relatorio relatorio;
    @OneToOne(mappedBy = "fkServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Avaliacao avaliacao;
    @ManyToOne
    @JoinColumn(name = "fkFichaServico")
    private FichaServico fkFichaServico;
    @ManyToOne
    @JoinColumn(name = "fkCliente")
    Cliente cliente;

    public Servico() {
        this.status = "Aguardando Confirmacao";
    }
}
