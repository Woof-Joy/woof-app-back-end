package org.woof.woofjoybackend.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClientController {
    private final ServiceUser serviceUser;
    private final List<Usuario> clientes;
    private final Usuario clienteLogado;

    @Autowired
    public ClientController(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
        this.clientes = serviceUser.getClientes();

        int indexUsuarioLogado = serviceUser.getIndexUsuarioLogado();
        if (indexUsuarioLogado >= 0 && indexUsuarioLogado < clientes.size()) {
            this.clienteLogado = clientes.get(indexUsuarioLogado);
        } else {
            this.clienteLogado = null;
        }
    }

    @GetMapping("/perfil")
    public ResponseEntity<Usuario> getPerfil() {
        if (clienteLogado == null) {
            return ResponseEntity.status(404).body(clienteLogado);
        }
        return ResponseEntity.status(200).body(clienteLogado);
    }

}
//    @PostMapping
//    public ResponseEntity<Item> postItem(@RequestBody Item it) {
//        return clienteLogado.postItem(it);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Item> putItem(@RequestBody Item it, @PathVariable int id) {
//        return clienteLogado.putItem(id, it);
//    }
//
//    @GetMapping("/itens/{id}")
//    public ResponseEntity<Item> getItemById(@RequestBody Item it, @PathVariable int id) {
//        return clienteLogado.OneItemGet(id);
//    }
//
//    @GetMapping("/itens")
//    public ResponseEntity<List<Item>> getAllItens() {
//        return clienteLogado.AllItensGet();
//    }
//
//    @GetMapping("/itens")
//    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
//        return clienteLogado.deleteItem(id);
//    }
//
//    @GetMapping("/pets")
//    public ResponseEntity<Pet> postPet(@RequestBody Pet pet) {
//        return clienteLogado.postPet(pet);
//    }
//
//
//}
