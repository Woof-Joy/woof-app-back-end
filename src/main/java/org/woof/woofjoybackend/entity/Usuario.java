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


import java.time.LocalDate;
@Getter
@Setter
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

}