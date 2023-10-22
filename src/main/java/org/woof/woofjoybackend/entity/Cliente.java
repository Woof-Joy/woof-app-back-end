package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCliente;
//    MUDO AQUI

    @OneToOne
    private Usuario usuario;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente() {
    }

    public Usuario getUsuario() {
        return usuario;
    }



    //    public ResponseEntity<Pet> postPet(Pet it) {
//        it.setId(petList.size() + 1);
//        petList.add(it);
//        return ResponseEntity.status(200).body(it);
//    }
//
//
//    public ResponseEntity<List<Pet>> allPetsGet() {
//        if (petList == null || petList.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(200).body(petList);
//    }
//
//
//    public ResponseEntity<Pet> OnePetGet(int id) {
//        int IndexForId = transformaIdEmIndexPet(id, petList);
//        if (verificaIndex(IndexForId)) {
//            return ResponseEntity.status(404).build();
//        }
//        return ResponseEntity.status(200).body(petList.get(IndexForId));
//    }
//
//    public ResponseEntity<Pet> putPet(int id, Pet it) {
//        int IndexForId = transformaIdEmIndexPet(id, petList);
//        if (verificaIndex(IndexForId)) {
//            return ResponseEntity.status(404).build();
//        }
//        it.setId(petList.get(IndexForId).getId());
//        petList.set(IndexForId, it);
//        return ResponseEntity.status(200).body(it);
//    }
//
//
//    public ResponseEntity<Void> deletePet(int id) {
//        int IndexForId = transformaIdEmIndexPet(id, petList);
//        if (verificaIndex(IndexForId)) {
//            return ResponseEntity.status(404).build();
//        }
//        petList.remove(IndexForId);
//        return ResponseEntity.status(204).build();
//
//    }
}
