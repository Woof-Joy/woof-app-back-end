package org.woof.woofjoybackend.domain;

import org.springframework.http.ResponseEntity;
import org.woof.woofjoybackend.entity.object.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Usuario implements iVerificaveis {
    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String cep;
    private String numero;
    private String email;
    private String senha;
    private Date dataNasc;
    private List<Item> itemList = new ArrayList<>();


    //o Construtor recebe id msm?
    public Usuario(String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, Date dataNasc) {
        this.id = 0;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.cep = cep;
        this.numero = numero;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.itemList = new ArrayList<>();
    }

    public Usuario() {
    }


    public ResponseEntity<Item> postItem(Item it) {
        it.setId(itemList.size()+1);
        itemList.add(it);
        return ResponseEntity.status(200).body(it);
    }


    public ResponseEntity<List<Item>> AllItensGet() {

        if (itemList == null || itemList.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(itemList);
    }

    public ResponseEntity<Item> OneItemGet(int id) {
        int IndexForId = transformaIdEmIndexItem(id, itemList);
        return ResponseEntity.status(200).body(itemList.get(IndexForId));
    }

    public ResponseEntity<Item> putItem(int id, Item it) {
        int IndexForId = transformaIdEmIndexItem(id, itemList);
        it.setId(itemList.get(IndexForId).getId());
        itemList.set(IndexForId, it);
        return ResponseEntity.status(200).body(it);
    }

    public ResponseEntity<Void> deleteItem(int id) {
        int IndexForId = transformaIdEmIndexItem(id, itemList);
        itemList.remove(IndexForId);
        return ResponseEntity.status(204).build();

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Item> getItemList() {
        return itemList;
    }


}

