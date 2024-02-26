package org.woof.woofjoybackend.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Observacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @NotBlank
    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^(alergia|doença|medicação)$", message = "O tipo deve ser 'alergia', 'doença' ou 'medicação'")
    private String tipo;
    @JoinColumn(name = "cachorro")
    @ManyToOne
    private Dog cachorro;



}
