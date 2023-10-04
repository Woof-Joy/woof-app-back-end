package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Usuario;

import org.woof.woofjoybackend.service.ServiceCliente;
import org.woof.woofjoybackend.service.ServiceParceiro;
import org.woof.woofjoybackend.service.ServiceUser;
import org.woof.woofjoybackend.entity.object.Item;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UsuarioController {
    private ServiceUser service;
    private ServiceCliente serviceCliente;
    private ServiceParceiro serviceParceiro;

    public UsuarioController(ServiceUser service, ServiceCliente serviceCliente, ServiceParceiro serviceParceiro) {
        this.service = service;
        this.serviceCliente = serviceCliente;
        this.serviceParceiro = serviceParceiro;
    }


    //CRUD - USUARIO

    @PostMapping("/{tipo}")
    public ResponseEntity<Usuario> postUsuario(
            @Valid @RequestBody Usuario usuario,
            @PathVariable int tipo) {
        if (service.usuarioPodeSerCadastrado(usuario, tipo)) {
            service.postUsuario(usuario, tipo);
            return ResponseEntity.status(201).body(usuario);
        }
        return ResponseEntity.status(409).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUsuario(
            @Valid @RequestBody Usuario usuario,
             @PathVariable int id) {

        if (service.putUsuario(usuario, id)) {
            return ResponseEntity.status(201).body(usuario);
        }

        return ResponseEntity.status(404).build();

    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers() {
        List<Usuario> listaUsuarios = service.listaUsuarios();

        if (!listaUsuarios.isEmpty()) {
            return ResponseEntity.status(200).body(listaUsuarios);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listaUsuarioPorId(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        if (service.idExiste(id)) {
            service.deleteUsuario(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/itens/{idUsuario}")
    public ResponseEntity<Item> cadastrarItem(@Valid @RequestBody Item item, @PathVariable Integer idUsuario) {
        service.cadastrarItem(item, idUsuario);
        return ResponseEntity.status(201).body(item);
    }

    @GetMapping("/itens/{idUsuario}/{idItem}")
    public ResponseEntity<Item> listaItem(@PathVariable Integer idUsuario, @PathVariable Integer idItem) {
        Usuario usuario = service.listaUsuarioPorId(idUsuario);
        Item item = service.listaItemPorId(idItem);

        if (item != null) {
            return ResponseEntity.status(200).body(item);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/itens/{idUsuario}")
    public ResponseEntity<List<Item>> listaItensUsuario(@PathVariable Integer idUsuario) {
        Usuario usuario = service.listaUsuarioPorId(idUsuario);
        List<Item> listaItens = usuario.getListaItens();

        if (!listaItens.isEmpty()) {
            return ResponseEntity.status(200).body(listaItens);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/itens")
    public ResponseEntity<List<Item>> listaTodosItens() {
        List<Usuario> listaUsuarios = service.listaUsuarios();
        List<Item> listaItens = new ArrayList<>();

        for (Usuario u : listaUsuarios) {
            listaItens.addAll(u.getListaItens());
        }

        if (!listaItens.isEmpty()) {
            return ResponseEntity.status(200).body(listaItens);
        }
        return ResponseEntity.status(204).build();
    }

//    @GetMapping("/itens")
//    public ResponseEntity<List<Item>> listaItensByCategoria() {
//        List<Usuario> listaUsuarios = service.listaUsuarios();
//        List<Item> listaItens = new ArrayList<>();
//
//        if (!listaItens.isEmpty()) {
//            return ResponseEntity.status(200).body(listaItens);
//        }
//        return ResponseEntity.status(204).build();
//    }

    @PutMapping("/itens/{idUsuario}/{idItem}")
    public ResponseEntity<Item> attItem(@Valid @RequestBody Item it, @PathVariable Integer idUsuario, Integer idItem) {
        Usuario usuario = service.listaUsuarioPorId(idUsuario);
        if (service.listaItemPorId(idItem) != null) {
            return ResponseEntity.status(200).body(service.attItem(it, idItem));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/itens/{idUsuario}/{idItem}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer idUsuario, @PathVariable Integer idItem) {
        Usuario usuario = service.listaUsuarioPorId(idUsuario);
        if (service.listaItemPorId(idItem) != null) {
            service.deleteItem(idItem);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
//    @PostMapping("/login/cliente")
//    public ResponseEntity<String> loginCliente(@RequestBody Cliente usuario) {
//        return service.loginUsuario(usuario.getEmail(), usuario.getSenha());
//    }
//
//    @PostMapping("/login/profissional")
//    public ResponseEntity<String> loginProfissional(@RequestBody Parceiro usuario) {
//        return service.loginUsuario(usuario.getEmail(), usuario.getSenha());
//    }
//    }
}
