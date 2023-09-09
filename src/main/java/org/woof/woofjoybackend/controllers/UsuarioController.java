package org.woof.woofjoybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Prestador;
import org.woof.woofjoybackend.entity.object.Item;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private ServiceUser serviceUser;
    List<Usuario> usuarios;

    @PostMapping
    public ResponseEntity<Usuario> cadastar(@RequestBody Cliente usuario) {
        serviceUser.cadastrarUsuario(usuario);
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






}
