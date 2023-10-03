package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.woof.woofjoybackend.domain.iVerificaveis;
import org.woof.woofjoybackend.entity.object.Item;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @PastOrPresent
    private LocalDate dataNasc;

    @Size(max = 500)
    private String descricao;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Parceiro parceiro;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> listaItens;

    public Usuario() {
        this.listaItens = new ArrayList<>();
    }

//
//    public ResponseEntity<Void> deleteItem(int id) {
//        int IndexForId = transformaIdEmIndexItem(id, itemList);
//        if (verificaIndex(IndexForId)) {
//            return ResponseEntity.status(404).build();
//        }
//        itemList.remove(IndexForId);
//        return ResponseEntity.status(204).build();
//
//    }


//    public Integer getId() {
//        return id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public String getSobrenome() {
//        return sobrenome;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public String getCep() {
//        return cep;
//    }
//
//    public String getNumero() {
//        return numero;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getSenha() {
//        return senha;
//    }
//
//    public LocalDate getDataNasc() {
//        return dataNasc;
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public Parceiro getParceiro() {
//        return parceiro;
//    }
//
//    public Cliente getCliente() {
//        return cliente;
//    }
}