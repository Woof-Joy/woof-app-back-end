package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.repository.ClienteRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;

import java.util.List;

@Service
public class ServiceCliente {
    private UsuarioRepository usuarioRepository;
    private ClienteRepository clienteRepository;

    public ServiceCliente(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    public Cliente registrarCliente(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return clienteRepository.save(new Cliente(usuario));
    }

    public List<Cliente> listaClientes() {
        return clienteRepository.findAll();
    }

    public Cliente listaClientePorId(Integer id) {
        return clienteRepository.findById(id).get();
    }

    public Cliente attCliente(Cliente cliente, Integer id) {
        cliente.setIdCliente(id);
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public boolean idExiste(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return clienteRepository.existsByUsuario(usuario);
    }
}
