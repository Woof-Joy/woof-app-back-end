package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.service.ServiceCliente;
import org.woof.woofjoybackend.service.ServiceDog;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ServiceCliente serviceCliente;
    private ServiceUser serviceUser;
    private ServiceDog serviceDog;


    public ClienteController(ServiceCliente serviceCliente, ServiceUser serviceUser, ServiceDog serviceDog) {
        this.serviceCliente = serviceCliente;
        this.serviceUser = serviceUser;
        this.serviceDog = serviceDog;
    }



    @GetMapping()
    public ResponseEntity<List<Cliente>> listaClientes() {
        List<Cliente> lista = serviceCliente.listaClientes();

        if (!lista.isEmpty()) {
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listaClientePorId(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceCliente.listaClientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> attCliente(@Valid @RequestBody Cliente cliente, @PathVariable Integer id) {
        if (serviceCliente.idExiste(id)) {
            return ResponseEntity.status(200).body(serviceCliente.attCliente(cliente, id));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        if (serviceCliente.idExiste(id)) {
            serviceCliente.deleteCliente(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

//    private Cliente clienteLogado() {
//        int index = serviceUser.indexUsuarioLogado;
//        if (index < 0) {
//            return null;
//        }
//        Cliente clienteLogado = (Cliente) clientes.get(index);
//
//        return clienteLogado;
//    }


//    @GetMapping("/perfil")
//    public ResponseEntity<Usuario> getPerfil() {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return ResponseEntity.status(200).body(clienteLogado());
//    }
//
//    @PostMapping("/itens")
//    public ResponseEntity<Item> postItem(@RequestBody Item it) {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().postItem(it);
//    }
//
//    @PutMapping("/itens/{id}")
//    public ResponseEntity<Item> putItem(@RequestBody Item it, @PathVariable int id) {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().putItem(id, it);
//    }
//
//    @GetMapping("/itens/{id}")
//    public ResponseEntity<Item> getItemById(@PathVariable int id) {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().OneItemGet(id);
//    }
//
//    @GetMapping("/itens")
//    public ResponseEntity<List<Item>> getAllItens() {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().AllItensGet();
//    }
//
//    @DeleteMapping("/itens/{id}")
//    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().deleteItem(id);
//    }
//
//
//    @GetMapping("/pets")
//    public ResponseEntity<List<Pet>> getPets() {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().allPetsGet();
//    }
//
//    @GetMapping("/pets/{id}")
//    public ResponseEntity<Pet> getOnePet(@PathVariable int id) {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().OnePetGet(id);
//    }
//
//    @PostMapping("/pets")
//    public ResponseEntity<Pet> postPet(@RequestBody Pet pet) {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().postPet(pet);
//    }
//
//    @PutMapping("/pets/{id}")
//    public ResponseEntity<Pet> putPet(@PathVariable int id, @RequestBody Pet pet) {
//        if (clienteLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return clienteLogado().putPet(id, pet);
//    }
//
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Void> postPet(@PathVariable int id) {
        if (clienteLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return clienteLogado().deletePet(id);
    }

}
