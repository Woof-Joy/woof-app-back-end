package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.woof.woofjoybackend.domain.iVerificaveis;
import org.woof.woofjoybackend.entity.response.Cep;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Usuario implements iVerificaveis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String nome;

    @Size(max = 50)
    private String sobrenome;

    @CPF
    private String cpf;

    @Size(min = 8, max = 8)
    private String cep;

    @Size(max = 10)
    private String numero;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @Past
    private LocalDate dataNasc;

    private String imgUsuario;

    @Size(max = 500)
    private String descricao;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Parceiro parceiro;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> listaItens;


    @OneToOne(mappedBy = "fkParceiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    public Usuario() {
        this.listaItens = new ArrayList<>();
    }


    public Optional<Cliente> getCliente(){
        return this.cliente != null ? Optional.of(this.cliente) : Optional.empty();
    }

    public Optional<Parceiro> getParceiro(){
        return this.parceiro != null ? Optional.of(this.parceiro) : Optional.empty();
    }

    public String getRole(){
        if (getParceiro().isPresent()) {
            if (getCliente().isPresent()) {
                return "A";
            } else {
                return "P";
            }
        } else {
            return "C";
        }
    }

}

