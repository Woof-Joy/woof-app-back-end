package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cachorro")
@AllArgsConstructor
@Getter
@Setter
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    @Size(max = 50)
    private String nome;

    @Past
    private LocalDate dtNasc;

    @Size(max = 200)
    private String imgCachorro;

    @BooleanFlag
    private Boolean rga;

    @NotNull
    @DecimalMax("30.0")
    private Double peso;

    @NotBlank
    @Size(max = 70)
    private String raca;

    @NotNull
    @Pattern(regexp = "^(pequeno|médio|grande)$", message = "O tamanho deve ser 'pequeno', 'médio' ou 'grande'")
    private String porte;

    @BooleanFlag
    private Boolean convenio;

    @Size(max = 50)
    private String carteirinha;

    @Pattern(regexp = "^[MF]$", message = "O gênero deve ser 'M' ou 'F'")
    private String genero;

    @Min(0)
    @Max(5)
    private Integer agressivo;

    @BooleanFlag
    private Boolean deficiencia;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "fkDono")
    private Cliente fkDono;

    @OneToMany(mappedBy = "cachorro", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Observacao> observacaoList;

    public Dog() {
        this.observacaoList = new ArrayList<>();
    }
}
