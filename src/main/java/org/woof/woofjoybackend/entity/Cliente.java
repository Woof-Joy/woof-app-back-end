package org.woof.woofjoybackend.entity;

import org.springframework.http.ResponseEntity;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.domain.iVerificaveis;
import org.woof.woofjoybackend.entity.object.Pet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Usuario implements iVerificaveis {
    private List<Pet> petList = new ArrayList<>();

    public Cliente(String nome, String sobrenome, String cpf, String cep, String numero, String email, String senha, Date dataNasc) {
        super(nome, sobrenome, cpf, cep, numero, email, senha, dataNasc);
        this.petList = new ArrayList<>();
    }

    public Cliente() {
    }

    @Override
    public void putPerfil(Usuario usuario, Usuario login) {

    }


    public ResponseEntity<Pet> postPet(Pet it) {
        it.setId(petList.size()+1);
        petList.add(it);
        return ResponseEntity.status(200).body(it);
    }


    public ResponseEntity<List<Pet>> allPetsGet() {
        if (petList == null || petList.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(petList);
    }


    public ResponseEntity<Pet> OnePetGet(int id) {
        int IndexForId = transformaIdEmIndexPet(id, petList);
        return ResponseEntity.status(200).body(petList.get(IndexForId));
    }

    public ResponseEntity<Pet> putPet(int id, Pet it ) {
        int IndexForId = transformaIdEmIndexPet(id, petList);
        it.setId(petList.get(IndexForId).getId());
        petList.set(IndexForId, it);
        return ResponseEntity.status(200).body(it);
    }


    public ResponseEntity<Void> deletePet(int id) {
        int IndexForId = transformaIdEmIndexPet(id, petList);
        petList.remove(IndexForId);
        return ResponseEntity.status(204).build();

    }

    public List<Pet> getPetList() {
        return petList;
    }

}
