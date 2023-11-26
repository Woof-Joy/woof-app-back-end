package org.woof.woofjoybackend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.ClienteDTO;
import org.woof.woofjoybackend.dto.ClienteDogDTO;
import org.woof.woofjoybackend.dto.ClientePerfilDTO;
import org.woof.woofjoybackend.dto.mapper.ClienteMapper;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.service.ServiceCliente;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ServiceCliente serviceCliente;

    private ServiceUser serviceUser;

    @Autowired
    public ClienteController(ServiceCliente serviceCliente, ServiceUser serviceUser) {
        this.serviceCliente = serviceCliente;
        this.serviceUser = serviceUser;

    }

    @GetMapping()
    public ResponseEntity<List<ClientePerfilDTO>> listaClientes() {
        List<Cliente> lista = serviceCliente.listaClientes();

        if (!lista.isEmpty()) {
            return ResponseEntity.status(200).body(ClienteMapper.toPerfilDTO(lista));
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClientePorId(@PathVariable Integer id) {
       Optional <Cliente> c = serviceCliente.listaClientePorId(id);
       return  c.isEmpty()?
               ResponseEntity.status(404).build():
               ResponseEntity.status(200).body(ClienteMapper.toDTO(c.get()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientePerfilDTO> attCliente(@Valid @RequestBody Cliente cliente, @PathVariable Integer id) {
        if (serviceCliente.idExiste(id)) {
            return ResponseEntity.status(200).body(ClienteMapper.toPerfilDTO(serviceCliente.attCliente(cliente, id)));
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

