package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ObservacaoDTO;
import org.woof.woofjoybackend.entity.Observacao;

import java.util.ArrayList;
import java.util.List;

public class ObservacaoMapper {
    public static ObservacaoDTO toDTO(Observacao entidadeObs){
        ObservacaoDTO observacaoDTO = new ObservacaoDTO();
        observacaoDTO.setNome(entidadeObs.getNome());
        observacaoDTO.setTipo(entidadeObs.getTipo());
        return observacaoDTO;
    }
    public static List<ObservacaoDTO> toDTO(List<Observacao> listaDeEntidadeObs){
        List<ObservacaoDTO> listaDeObsDTO = new ArrayList<>();
        for (Observacao obs:
             listaDeEntidadeObs) {
            listaDeObsDTO.add(ObservacaoMapper.toDTO(obs));
        }
        return listaDeObsDTO;
    }
}
