package org.woof.woofjoybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {
    private final ServiceUser serviceUser;
    private List<Usuario> usuarios;

    @Autowired
    public FeedController(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
        this.usuarios = serviceUser.getPrestadores();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> trazer() {
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return ResponseEntity.status(200).body(usuario);
    }

    @PutMapping("/{i}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int i, @RequestBody Usuario usuario) {
        usuarios.set(i, usuario);
        return ResponseEntity.status(200).body(usuario);

    }

    @DeleteMapping("/{i}")
    public ResponseEntity<Void> deletar(@PathVariable int i) {
        usuarios.remove(i);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{i}")
    public ResponseEntity<Usuario> buscar(@PathVariable int i) {
        return ResponseEntity.status(200).body(usuarios.get(i));
    }


}
