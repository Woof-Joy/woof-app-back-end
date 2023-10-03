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

    @PostMapping("/{tipo}" )
    public ResponseEntity<Usuario> cadastrarUsuario(
             @RequestBody Usuario usuario,
            @PathVariable int tipo) {
        if (service.emailExiste(usuario.getEmail())) {
            if (service.cadastrado(usuario, tipo)){
                return ResponseEntity.status(409).build();
            }
            service.cadastrarUsuario(usuario, tipo);
        }


        return ResponseEntity.status(201).body(usuario);


    }




//    @GetMapping()
//    public ResponseEntity<List<Cliente>> listaClientes() {
//        List<Cliente> lista = serviceCliente.listaClientes();
//
//        if (!lista.isEmpty()) {
//            return ResponseEntity.status(200).body(lista);
//        }
//        return ResponseEntity.status(204).build();
//    }


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

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> attUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Integer id) {
        if (service.idValido(id)) {
            if (!service.emailExiste(usuario.getEmail())) {
                return ResponseEntity.status(200).body(service.attUsuario(usuario, id));
            }
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        if (service.idValido(id)) {
            service.deleteUsuario(id);
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
