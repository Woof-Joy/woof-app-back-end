package org.woof.woofjoybackend.controllers.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Profissional;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ServiceUserController {
    @Autowired
    private ServiceUser serviceUser;


    @PostMapping("/cliente")
    public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente usuario) {
        return serviceUser.cadastrarUsuario(usuario);

    }
    @PostMapping("/profissional")
    public ResponseEntity<String> cadastrarProfissional(@RequestBody Profissional usuario) {
        return serviceUser.cadastrarUsuario(usuario);

    }

    @PostMapping("/login/cliente")
    public ResponseEntity<String> loginCliente(@RequestBody Cliente usuario) {
        return serviceUser.loginUsuario(usuario.getEmail(), usuario.getSenha());
    }
    @PostMapping("/login/profissional")
    public ResponseEntity<String> loginProfissional(@RequestBody Profissional usuario) {
        return serviceUser.loginUsuario(usuario.getEmail(), usuario.getSenha());
    }
    @PutMapping("/profissional/{id}")
    public ResponseEntity<String> putProfissional(@PathVariable("id") int id, @RequestBody Profissional usuario) {
        return serviceUser.putUsuario(id, usuario);
    }
    @PutMapping("/cliente/{id}")
    public ResponseEntity<String> putCliente(@PathVariable("id") int id,@RequestBody Cliente usuario) {
        return serviceUser.putUsuario(id, usuario);
    }
    @DeleteMapping("/profissional/{id}")
    public ResponseEntity<Void> deleteProfissional(@PathVariable("id") int id) {
        return serviceUser.deleteUsuario(id);
    }
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") int id) {
        return serviceUser.deleteUsuario(id);
    }


    @GetMapping("/profissional")
    public ResponseEntity<List<Usuario>> getProfissionais() {
        return ResponseEntity.status(200).body(serviceUser.getProfissionais());
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<Usuario>> getClientes() {
        return ResponseEntity.status(200).body(serviceUser.getClientes());
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers() {
        return serviceUser.getUsers();
    }


}
