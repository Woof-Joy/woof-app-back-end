package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.EnderecoParceiroClienteDTO;
import org.woof.woofjoybackend.dto.UsuarioEnderecoFeedDTO;
import org.woof.woofjoybackend.entity.Endereco;

public class EnderecoMapper {
    public static UsuarioEnderecoFeedDTO toDTO(Endereco entidadeCep){
        if (entidadeCep == null) return null;
        UsuarioEnderecoFeedDTO enderecoDTO = new UsuarioEnderecoFeedDTO();
        enderecoDTO.setLocalidade(entidadeCep.getLocalidade());
//        enderecoDTO.setBairro(entidadeCep.getBairro());
        return enderecoDTO;
    }

    public static EnderecoParceiroClienteDTO toDTOParceiroCleinte(Endereco entidadeCep){
        if (entidadeCep == null) return null;
        EnderecoParceiroClienteDTO enderecoDTO = new EnderecoParceiroClienteDTO();
        enderecoDTO.setRua(entidadeCep.getLogradouro());
        enderecoDTO.setCidade(entidadeCep.getLocalidade());
        enderecoDTO.setEstado(entidadeCep.getUf());
        return enderecoDTO;
    }


}
