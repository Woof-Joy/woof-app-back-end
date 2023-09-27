package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.woof.woofjoybackend.domain.iVerificaveis;


import java.time.LocalDate;

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

    public Usuario(String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, LocalDate dataNasc, String descricao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.cep = cep;
        this.numero = numero;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.descricao = descricao;
    }

    public Usuario() {
    }


//    public ResponseEntity<Item> postItem(Item it) {
//        it.setId(itemList.size() + 1);
//        itemList.add(it);
//        return ResponseEntity.status(200).body(it);
//    }
//
//
//    public ResponseEntity<List<Item>> AllItensGet() {
//
//        if (itemList.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(200).body(itemList);
//    }
//
//    public ResponseEntity<Item> OneItemGet(int id) {
//        int IndexForId = transformaIdEmIndexItem(id, itemList);
//        if (verificaIndex(IndexForId)) {
//            return ResponseEntity.status(404).build();
//        }
//        return ResponseEntity.status(200).body(itemList.get(IndexForId));
//    }
//
//    public ResponseEntity<Item> putItem(int id, Item it) {
//        int IndexForId = transformaIdEmIndexItem(id, itemList);
//        if (verificaIndex(IndexForId)) {
//            return ResponseEntity.status(404).build();
//        }
//        it.setId(itemList.get(IndexForId).getId());
//        itemList.set(IndexForId, it);
//        return ResponseEntity.status(200).body(it);
//    }
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}