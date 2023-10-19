package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Usuario;

import org.woof.woofjoybackend.service.ServiceCliente;
import org.woof.woofjoybackend.service.ServiceParceiro;
import org.woof.woofjoybackend.service.ServiceUser;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario( @PathVariable Integer id) {
        if (service.deleteUsuario(id)) {
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

}
