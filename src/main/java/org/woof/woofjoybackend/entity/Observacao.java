package org.woof.woofjoybackend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Observacao {

    @Id
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String tipo;
    @JoinColumn(name = "cachorro")
    @ManyToOne
    private Dog cachorro;





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Dog getCachorro() {
        return cachorro;
    }

    public void setCachorro(Dog cachorro) {
        this.cachorro = cachorro;
    }


}
