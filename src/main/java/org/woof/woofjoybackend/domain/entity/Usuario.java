package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Usuario {
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
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    private String imgUsuario;

    @Past
    private LocalDate dataNasc;
    
    @Size(max = 500)
    private String descricao;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Parceiro parceiro;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> listaItens;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private DonoImagem donoImagem;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
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

    public String getImgUsuario() {
        for (Imagem i : this.getDonoImagem().getImagens()){
            if (i.getTipo().equals("perfil")){
                return i.getUrlImagem();
            }
        }
        return "https://woofjoy-s3.s3.amazonaws.com/usuario_sem_perfil.png";
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

