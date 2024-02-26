package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.AvaliacaoDTO;
import org.woof.woofjoybackend.domain.entity.Avaliacao;

import java.util.List;

public class AvaliacaoMapper {
    public static AvaliacaoDTO toDTO(Avaliacao entidadeAvaliacao){
        if (entidadeAvaliacao == null) return null;
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(entidadeAvaliacao.getId());
        avaliacaoDTO.setNota(entidadeAvaliacao.getNota());
        avaliacaoDTO.setComentario(entidadeAvaliacao.getComentario());
        return avaliacaoDTO;
    }
    public static Double toDouble(List<AvaliacaoDTO> listaDeAvaliacoes){
        if (listaDeAvaliacoes.isEmpty()) return null;
        Integer totalDeAvaliacoes = listaDeAvaliacoes.size();
        Double acumulador = 0.0;
        for (AvaliacaoDTO avaliacao:
             listaDeAvaliacoes) {
            acumulador += avaliacao.getNota();
        }
        return acumulador/totalDeAvaliacoes;
    }
}
