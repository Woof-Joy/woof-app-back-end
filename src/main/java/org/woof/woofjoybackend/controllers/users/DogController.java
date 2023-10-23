package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.object.Dog;
import org.woof.woofjoybackend.domain.ListaObj;
import org.woof.woofjoybackend.entity.object.ManipuladorDeArquivo;

import org.woof.woofjoybackend.service.ServiceDog;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private ServiceDog serviceDog;




    @GetMapping("dono/{id}")
    public ResponseEntity<List<Dog>> listarPet(@PathVariable Integer id) {
        List<Dog> dogsCadastrados = serviceDog.listarDogs(id);
        return dogsCadastrados == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(dogsCadastrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> listarUmPet(@PathVariable Integer id) {
        Dog dogCadastrado = serviceDog.listarDog(id);
        return dogCadastrado == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(dogCadastrado);
    }

    @PostMapping("/{idDono}")
    public ResponseEntity<Dog> cadastrarPet(@Valid @RequestBody Dog dog, @PathVariable Integer idDono) {
        Dog dogCriado = serviceDog.criarDog(dog, idDono);
        return dogCriado == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok().body(dogCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dog> atulizarPet(@PathVariable int id, @Valid @RequestBody Dog dog) {
        Dog dogAtualizado = serviceDog.atulizarDog(dog, id);
        return dogAtualizado == null ? ResponseEntity.notFound().build():ResponseEntity.ok().body(dogAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        Boolean dogDeletado = serviceDog.deletarDog(id);
        return dogDeletado?ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("csv/{idDono}")
    public ResponseEntity<Void> upload(@PathVariable Integer idDono) {
        List<Dog> list = serviceDog.listarDogs(idDono);
        if (!list.isEmpty()) {
            ListaObj<Dog> listaObj = new ListaObj<Dog>(list.size());
            for (Dog dog :
                    list) {
                listaObj.adicionar(dog);
            }

            ManipuladorDeArquivo.gravaArquivoCsv(listaObj, "lista-de-cachorros");

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("csv/ordenado-agrssivo/{idDono}")
    public ResponseEntity<Void> uploadOrdenado(@PathVariable Integer idDono) {
        List<Dog> list = serviceDog.listarDogs(idDono);
        if (!list.isEmpty()){
        ListaObj<Dog> listaObj = new ListaObj<Dog>(list.size());
        for (Dog dog:
                list) {
            listaObj.adicionar(dog);
        }

        ManipuladorDeArquivo.gravaArquivoCsv(ListaObj.ordenarPorAdressividade(listaObj), "lista-de-cachorros");

        return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
