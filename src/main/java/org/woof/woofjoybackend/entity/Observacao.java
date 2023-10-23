package org.woof.woofjoybackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.woof.woofjoybackend.entity.object.Dog;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Observacao {

    @Id
    private Integer id;
    private String nome;

    private String tipo;

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