package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.service.ServiceCliente;
import org.woof.woofjoybackend.service.ServiceParceiro;
import org.woof.woofjoybackend.service.ServiceUser;

import org.woof.woofjoybackend.service.autenticacao.UsuarioLoginDto;
import org.woof.woofjoybackend.service.autenticacao.UsuarioTokenDto;


import java.util.List;


    @RestController
    @RequestMapping("/users")
    public class UsuarioController {
        private ServiceUser service;
        private ServiceCliente serviceCliente;
        private ServiceParceiro serviceParceiro;
        private PasswordEncoder passwordEncoder;

        public UsuarioController(ServiceUser service, ServiceCliente serviceCliente, ServiceParceiro serviceParceiro, PasswordEncoder passwordEncoder) {
            this.service = service;
            this.serviceCliente = serviceCliente;
            this.serviceParceiro = serviceParceiro;
            this.passwordEncoder = passwordEncoder;
        }

//CRUD - USUARIO

        @PostMapping("/{tipo}")
        public ResponseEntity<Void> postUsuario(
                @Valid @RequestBody Usuario usuario,
                @PathVariable int tipo) {
            if (service.usuarioPodeSerCadastrado(usuario, tipo)) {
                service.postUsuario(usuario, tipo);
                return ResponseEntity.status(201).build();
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

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id, @RequestParam String role) {
            if (service.deleteUsuario(id, role)) {
                return ResponseEntity.status(204).build();
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

        @PostMapping("/login")
        public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
            UsuarioTokenDto usuarioToken = this.service.autenticar(usuarioLoginDto);
            return ResponseEntity.status(200).body(usuarioToken);
        }

    }
