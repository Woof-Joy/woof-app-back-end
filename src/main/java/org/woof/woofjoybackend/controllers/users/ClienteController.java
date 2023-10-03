package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.object.Dog;
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

    @Autowired
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
    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getPets() {
        List<Dog> dogsCadastrados = serviceDog.listarDogs();
        return dogsCadastrados == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(dogsCadastrados);
    }

    @GetMapping("/dogs/{id}")
    public ResponseEntity<Dog> getOnePet(@PathVariable int id) {
        Dog dogCadastrado = serviceDog.listarDog(id);
        return dogCadastrado == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(dogCadastrado);
    }

    @PostMapping("/dogs")
    public ResponseEntity<Dog> postPet(@Valid @RequestBody Dog dog) {
        Dog dogCriado = serviceDog.criarDog(dog);
        return ResponseEntity.ok().body(dogCriado);
    }

    @PutMapping("/dogs/{id}")
    public ResponseEntity<Dog> putPet(@PathVariable int id, @Valid @RequestBody Dog dog) {
        Dog dogAtualizado = serviceDog.atulizarDog(dog, id);
        return dogAtualizado == null ? ResponseEntity.notFound().build():ResponseEntity.ok().body(dogAtualizado);
    }

    @DeleteMapping("/dogs/{id}")
    public ResponseEntity<Void> postPet(@PathVariable int id) {
        Boolean dogDeletado = serviceDog.deletarDog(id);
        return dogDeletado?ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
