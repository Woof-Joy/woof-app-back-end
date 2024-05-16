package org.woof.woofjoybackend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.UsuarioCriacaoDTO;
import org.woof.woofjoybackend.dto.UsuarioDTO;
import org.woof.woofjoybackend.dto.mapper.UsuarioMapper;
import org.woof.woofjoybackend.domain.entity.Usuario;
import org.woof.woofjoybackend.service.users.ServiceUser;

import org.woof.woofjoybackend.service.autenticacao.UsuarioLoginDto;
import org.woof.woofjoybackend.service.autenticacao.UsuarioTokenDto;


import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UsuarioController {
    private ServiceUser service;

    public UsuarioController(ServiceUser service) {
        this.service = service;
    }

//CRUD - USUARIO

    @PostMapping("/{tipo}")
    public ResponseEntity<UsuarioDTO> postUsuario(
            @Valid @RequestBody UsuarioCriacaoDTO usuario,
            @PathVariable String tipo) {
        if (service.usuarioPodeSerCadastrado(usuario.getEmail(), tipo)) {
            return ResponseEntity.status(201).body(service.postUsuario(usuario, tipo));
        }
        return ResponseEntity.status(409).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> putUsuario(
            @Valid @RequestBody UsuarioCriacaoDTO usuario,
            @PathVariable int id) {
        return ResponseEntity.status(200).body(UsuarioMapper.toDto(service.putUsuario(usuario, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id, @RequestParam String role) {
        if (service.deleteUsuario(id, role)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsers() {
        List<Usuario> listaUsuarios = service.listaUsuarios();

        if (!listaUsuarios.isEmpty()) {
            return ResponseEntity.status(200).body(UsuarioMapper.toDtoList(listaUsuarios));
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(UsuarioMapper.toDto(service.listaUsuarioPorId(id)));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = this.service.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

}
