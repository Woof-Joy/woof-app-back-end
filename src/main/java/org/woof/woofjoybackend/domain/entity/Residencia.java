package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Residencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResidencia;
    private String tipoResidencia;
    @BooleanFlag
    @Value(value = "false")
    private Boolean areaExterna;
    @BooleanFlag
    @Value(value = "false")
    private Boolean temAnimais;
    @BooleanFlag
    @Value(value = "false")
    private Boolean temCriancas;
    @BooleanFlag
    @Value(value = "false")
    private Boolean rotaFuga;
    @BooleanFlag
    @Value(value = "false")
    private Boolean dogSofaCama;
    @OneToOne
    @JoinColumn(name = "fichaServico_id")
    private FichaServico fkFichaServico;
}
