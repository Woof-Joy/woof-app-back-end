package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Boolean areaExterna;
    @BooleanFlag
    private Boolean temAnimais;
    @BooleanFlag
    private Boolean temCriancas;
    @BooleanFlag
    private Boolean rotaFuga;
    @BooleanFlag
    private Boolean dogSofaCama;
}
