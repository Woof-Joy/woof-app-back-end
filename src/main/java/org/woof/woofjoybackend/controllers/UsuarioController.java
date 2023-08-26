package org.woof.woofjoybackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Prestador;
import org.woof.woofjoybackend.entity.object.Item;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Usuario> cadastar(@RequestBody Usuario usuario) {
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

    @GetMapping("/clientes")
    public ResponseEntity<List<Usuario>> getPrestadores() {
        List<Usuario> clientes = new ArrayList<>();
        for (Usuario us : usuarios) {
            if (us instanceof Cliente) {
                clientes.add(us);
            }
        }
        return ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/prestadores")
    public ResponseEntity<List<Usuario>> getClientes() {
        List<Usuario> prestadores = new ArrayList<>();
        for (Usuario us : usuarios) {
            if (us instanceof Prestador) {
                prestadores.add(us);
            }
        }
        return ResponseEntity.status(200).body(prestadores);
    }


}
