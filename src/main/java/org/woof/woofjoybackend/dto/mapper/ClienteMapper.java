package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ClientePerfilDTO;
import org.woof.woofjoybackend.entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {

    public static ClientePerfilDTO toDTO(Cliente entidadeCliente){
        if (entidadeCliente == null) return null;
        ClientePerfilDTO clientePerfilDTO = new ClientePerfilDTO();
        clientePerfilDTO.setIdCliente(entidadeCliente.getIdCliente());
        clientePerfilDTO.setNome(entidadeCliente.getUsuario().getNome());
        clientePerfilDTO.setSobrenome(entidadeCliente.getUsuario().getSobrenome());
        clientePerfilDTO.setNumero(entidadeCliente.getUsuario().getNumero());
        clientePerfilDTO.setEmail(entidadeCliente.getUsuario().getEmail());
        clientePerfilDTO.setIdCliente(entidadeCliente.getUsuario().getId());
        clientePerfilDTO.setDogList(DogMapper.toDTOCliente(entidadeCliente.getDogList()));
        return clientePerfilDTO;
    }

    public static List<ClientePerfilDTO> toDTO(List<Cliente> listaDeClientes){
        List<ClientePerfilDTO> listaDeClientesDTO = new ArrayList<>();
        for (Cliente cliente:
                listaDeClientes) {
            if (cliente == null) continue;
            listaDeClientesDTO.add(ClienteMapper.toDTO(cliente));
        }
        return listaDeClientesDTO;
    }
}
