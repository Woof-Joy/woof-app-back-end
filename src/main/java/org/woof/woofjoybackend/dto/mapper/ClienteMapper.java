package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ClienteDTO;
import org.woof.woofjoybackend.dto.ClientePerfilDTO;
import org.woof.woofjoybackend.domain.entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {

    public static ClientePerfilDTO toPerfilDTO(Cliente entidadeCliente){
        if (entidadeCliente == null) return null;
        ClientePerfilDTO clientePerfilDTO = new ClientePerfilDTO();
        clientePerfilDTO.setNome(entidadeCliente.getUsuario().getNome());
        clientePerfilDTO.setSobrenome(entidadeCliente.getUsuario().getSobrenome());
        clientePerfilDTO.setEmail(entidadeCliente.getUsuario().getEmail());
        clientePerfilDTO.setIdUser(entidadeCliente.getIdUser());
        return clientePerfilDTO;
    }

    public static List<ClientePerfilDTO> toPerfilDTO(List<Cliente> listaDeClientes){
        List<ClientePerfilDTO> listaDeClientesDTO = new ArrayList<>();
        for (Cliente cliente:
                listaDeClientes) {
            if (cliente == null) continue;
            listaDeClientesDTO.add(ClienteMapper.toPerfilDTO(cliente));
        }
        return listaDeClientesDTO;
    }

    public static ClienteDTO toDTO(Cliente entidadeCliente){
        if (entidadeCliente == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(entidadeCliente.getIdCliente());
        dto.setIdUser(entidadeCliente.getIdUser());
        return dto;
    }
}
