package org.woof.woofjoybackend.controllers.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.object.Item;
import org.woof.woofjoybackend.entity.object.Pet;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClientController {
    private ServiceUser serviceUser;
    private List<Usuario> clientes = new ArrayList<>();
    Cliente clienteLogado = (Cliente) clientes.get(serviceUser.getIndexUsuario());

    @Autowired
    public ClientController(ServiceUser serviceUser) {

        this.serviceUser = serviceUser;
        this.clientes = serviceUser.getClientes();
    }

    @GetMapping
    public ResponseEntity<Usuario> getPerfil() {
        if (clienteLogado == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(clienteLogado);
    }

    @PostMapping
    public ResponseEntity<Item> postItem(@RequestBody Item it) {
        return clienteLogado.postItem(it);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> putItem(@RequestBody Item it, @PathVariable int id) {
        return clienteLogado.putItem(id, it);
    }

    @GetMapping("/itens/{id}")
    public ResponseEntity<Item> getItemById(@RequestBody Item it, @PathVariable int id) {
        return clienteLogado.getOneItem(id);
    }

    @GetMapping("/itens")
    public ResponseEntity<List<Item>> getAllItens() {
        return clienteLogado.getAllItens();
    }

    @GetMapping("/itens")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        return clienteLogado.deleteItem(id);
    }

    @GetMapping("/pets")
    public ResponseEntity<Pet> postPet(@RequestBody Pet pet) {
        return clienteLogado.postPet(pet);
    }


}
