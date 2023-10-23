package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.service.ServiceCliente;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
//    MUDO AQUI

    private ServiceCliente serviceCliente;

    private ServiceUser serviceUser;

    @Autowired
    public ClienteController(ServiceCliente serviceCliente, ServiceUser serviceUser) {
        this.serviceCliente = serviceCliente;
        this.serviceUser = serviceUser;

    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> listaClientes() {
        List<Cliente> lista = serviceCliente.listaClientes();

        if (!lista.isEmpty()) {
            return ResponseEntity.status(200).body(lista);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listaClientePorId(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceCliente.listaClientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> attCliente(@Valid @RequestBody Cliente cliente, @PathVariable Integer id) {
        if (serviceCliente.idExiste(id)) {
            return ResponseEntity.status(200).body(serviceCliente.attCliente(cliente, id));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        if (serviceCliente.idExiste(id)) {
            serviceCliente.deleteCliente(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }


}

