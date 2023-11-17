package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ParceiroEnderecoFeedDTO;
import org.woof.woofjoybackend.entity.Endereco;

public class EnderecoMapper {
    public static ParceiroEnderecoFeedDTO toDTO(Endereco entidadeCep){
        if (entidadeCep == null) return null;
        ParceiroEnderecoFeedDTO enderecoDTO = new ParceiroEnderecoFeedDTO();
        enderecoDTO.setLocalidade(entidadeCep.getLocalidade());
        enderecoDTO.setBairro(entidadeCep.getBairro());
        return enderecoDTO;
    }
}
