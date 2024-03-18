package org.woof.woofjoybackend.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.domain.entity.Cliente;
import org.woof.woofjoybackend.repository.ClienteRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCliente {
    private UsuarioRepository usuarioRepository;
    private ClienteRepository clienteRepository;

    public ServiceCliente(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listaClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> listaClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente attCliente(Cliente cliente, Integer id) {
        cliente.setIdCliente(id);
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public boolean idExiste(Integer id) {
        return clienteRepository.findByUsuarioId(id).isPresent();
    }
}
