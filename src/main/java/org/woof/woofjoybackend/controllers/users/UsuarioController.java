package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.domain.iVerificaveis;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.repository.UsuarioRepository;
import org.woof.woofjoybackend.service.ServiceUser;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private ServiceUser service;

    @PostMapping()
    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        if (emailValido(usuario)) {
            service.cadastrarUsuario(usuario);
            return ResponseEntity.status(201).body(usuario);
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers() {
        List<Usuario> listaUsuarios = service.getUsers();

        if (!listaUsuarios.isEmpty()) {
            return ResponseEntity.status(200).body(listaUsuarios);
        }
        return ResponseEntity.status(204).build();
    }

    @PutMapping()
    public ResponseEntity<Usuario> attUsuario(@Valid @RequestBody Usuario usuario) {
            if (emailValido(usuario)){
                service.cadastrarUsuario(usuario);
                return ResponseEntity.status(200).body(usuario);
            }
            return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
        if (service.existe(id)) {
            service.deleteUsuario(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    public boolean emailValido(Usuario usuario) {
        List<Usuario> listaUsuarios = service.getUsers();

        for (Usuario u : listaUsuarios) {
            if (u.getEmail().equals(usuario.getEmail()) && u.getId() != usuario.getId()) {
                return false;
            }
        }
        return true;
    }

//    @PostMapping("/cliente")
//    public ResponseEntity<String> cadastrarCliente(@Valid @RequestBody Cliente usuario) {
//
//        return service.cadastrarUsuario(usuario);
//    }

//    @PostMapping("/profissional")
//    public ResponseEntity<String> cadastrarProfissional(@RequestBody Parceiro usuario) {
//        return service.cadastrarUsuario(usuario);
//
//    }

//    @PostMapping("/login/cliente")
//    public ResponseEntity<String> loginCliente(@RequestBody Cliente usuario) {
//        return service.loginUsuario(usuario.getEmail(), usuario.getSenha());
//    }
//
//    @PostMapping("/login/profissional")
//    public ResponseEntity<String> loginProfissional(@RequestBody Parceiro usuario) {
//        return service.loginUsuario(usuario.getEmail(), usuario.getSenha());
//    }
//
//    @PutMapping("/profissional/{id}")
//    public ResponseEntity<String> putProfissional(@PathVariable("id") int id, @RequestBody Parceiro usuario) {
//        return service.putUsuario(id, usuario);
//    }
//
//    @PutMapping("/cliente/{id}")
//    public ResponseEntity<String> putCliente(@PathVariable("id") int id, @RequestBody Cliente usuario) {
//        return service.putUsuario(id, usuario);
//    }
//
//    @DeleteMapping("/profissional/{id}")
//    public ResponseEntity<Void> deleteProfissional(@PathVariable("id") int id) {
//        return service.deleteUsuario(id);
//    }
//
//    @DeleteMapping("/cliente/{id}")
//    public ResponseEntity<Void> deleteCliente(@PathVariable("id") int id) {
//        return service.deleteUsuario(id);
//    }
//
//
//    @GetMapping("/profissional")
//    public ResponseEntity<List<Usuario>> getProfissionais() {
//        return ResponseEntity.status(200).body(service.getProfissionais());
//    }
//
//    @GetMapping("/cliente")
//    public ResponseEntity<List<Usuario>> getClientes() {
//        return ResponseEntity.status(200).body(service.getClientes());
//    }
}
