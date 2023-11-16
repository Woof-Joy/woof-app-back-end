package org.woof.woofjoybackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Boolean areaExterna;
    private Boolean temAnimais;
    private Boolean temCriancas;
    private Boolean rotaFuga;
    private Boolean dogSofaCama;
}
