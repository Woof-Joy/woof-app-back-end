package org.woof.woofjoybackend.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.object.Item;
import org.woof.woofjoybackend.entity.object.Pet;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClientController {
    private final ServiceUser serviceUser;
    private final List<Usuario> clientes;

    @Autowired
    public ClientController(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
        this.clientes = serviceUser.getClientes();
    }

    private Cliente clienteLogado() {
        int index = serviceUser.indexUsuarioLogado;
        if (index < 0) {
            return null;
        }
        Cliente clienteLogado = (Cliente) clientes.get(index);

        return clienteLogado;
    }




    @GetMapping("/perfil")
    public ResponseEntity<Usuario> getPerfil() {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.status(200).body(clienteLogado());
    }

    @PostMapping("/itens")
    public ResponseEntity<Item> postItem(@RequestBody Item it) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().postItem(it);
    }

    @PutMapping("/itens/{id}")
    public ResponseEntity<Item> putItem(@RequestBody Item it, @PathVariable int id) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().putItem(id, it);
    }

    @GetMapping("/itens/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().OneItemGet(id);
    }

    @GetMapping("/itens")
    public ResponseEntity<List<Item>> getAllItens() {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().AllItensGet();
    }

    @DeleteMapping("/itens/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().deleteItem(id);
    }


    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getPets() {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().allPetsGet();
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getOnePet(@PathVariable int id) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().OnePetGet(id);
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> postPet(@RequestBody Pet pet) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().postPet(pet);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> putPet(@PathVariable int id, @RequestBody Pet pet) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().putPet(id, pet);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Void> postPet(@PathVariable int id) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().deletePet(id);
    }

}
