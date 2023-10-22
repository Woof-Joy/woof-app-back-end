package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.object.Dog;
import org.woof.woofjoybackend.service.ServiceDog;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private ServiceDog serviceDog;

    public DogController(ServiceDog serviceDog) {
        this.serviceDog = serviceDog;
    }


    @GetMapping()
    public ResponseEntity<List<Dog>> getPets() {
        List<Dog> dogsCadastrados = serviceDog.listarDogs();
        return dogsCadastrados == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dogsCadastrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getOnePet(@PathVariable int id) {
        Dog dogCadastrado = serviceDog.listarDog(id);
        return dogCadastrado == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(dogCadastrado);
    }

    @PostMapping()
    public ResponseEntity<Dog> postPet(@Valid @RequestBody Dog dog) {
        Dog dogCriado = serviceDog.criarDog(dog);
        return ResponseEntity.ok().body(dogCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dog> putPet(@PathVariable int id, @Valid @RequestBody Dog dog) {
        Dog dogAtualizado = serviceDog.atulizarDog(dog, id);
        return dogAtualizado == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(dogAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> postPet(@PathVariable int id) {
        Boolean dogDeletado = serviceDog.deletarDog(id);
        return dogDeletado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}