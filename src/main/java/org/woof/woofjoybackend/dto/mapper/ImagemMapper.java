package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ImagemDTO;
import org.woof.woofjoybackend.domain.entity.Imagem;

public class ImagemMapper {
    public static ImagemDTO toDTO(Imagem entidadeImagem){
        if (entidadeImagem == null) return null;
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setId(entidadeImagem.getId());
        imagemDTO.setTipo(entidadeImagem.getTipo());
        imagemDTO.setPath(entidadeImagem.getPath());
        imagemDTO.setUrlImagem(entidadeImagem.getUrlImagem());
        return imagemDTO;
    }
}
