package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.FichaServicoCriacaoDTO;
import org.woof.woofjoybackend.dto.FichaServicoDTO;
import org.woof.woofjoybackend.dto.ParceiroFichaServicoDTO;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.Parceiro;

import java.util.ArrayList;
import java.util.List;

public class FichaServicoMapper {
    public static FichaServicoDTO toDTO(FichaServico entidadeFichaServico){
        if (entidadeFichaServico == null) return null;
        FichaServicoDTO fichaServicoDTO = new FichaServicoDTO();
        fichaServicoDTO.setTipoServico(entidadeFichaServico.getTipoServico());
        fichaServicoDTO.setValor(entidadeFichaServico.getValor());
        return fichaServicoDTO;
    }
    public static List<FichaServicoDTO> toDTO(List<FichaServico> listaDeEntidadeFichaServico){
        List<FichaServicoDTO> listaDeFichaServicoDTO = new ArrayList<>();
        for (FichaServico servico:
                listaDeEntidadeFichaServico) {
            listaDeFichaServicoDTO.add(FichaServicoMapper.toDTO(servico));
        }
        return listaDeFichaServicoDTO;
    }

    public static ParceiroFichaServicoDTO toDTOServico(FichaServico entidadeFichaServico){
        if (entidadeFichaServico == null) return null;
        ParceiroFichaServicoDTO parceiroFichaServicoDTO = new ParceiroFichaServicoDTO();
        parceiroFichaServicoDTO.setId(entidadeFichaServico.getId());
        parceiroFichaServicoDTO.setTipoServico(entidadeFichaServico.getTipoServico());
        parceiroFichaServicoDTO.setValor(entidadeFichaServico.getValor());
        parceiroFichaServicoDTO.setServicos(ServicoMapper.toDTO(entidadeFichaServico.getServicos()));
        return parceiroFichaServicoDTO;
    }


    public static List<ParceiroFichaServicoDTO> toDTOServico(List<FichaServico> listaDeEntidadeFichaServico){
        List<ParceiroFichaServicoDTO> listaDeParceiroFichaServicoDTO = new ArrayList<>();
        for (FichaServico servico:
                listaDeEntidadeFichaServico) {
            listaDeParceiroFichaServicoDTO.add(FichaServicoMapper.toDTOServico(servico));
        }
        return listaDeParceiroFichaServicoDTO;
    }

    public static FichaServico toEntity(FichaServicoCriacaoDTO dto, Parceiro parceiro){
        FichaServico ficha = new FichaServico();

        ficha.setTipoServico(dto.getTipoServico());
        ficha.setValor(dto.getValor());
        ficha.setParceiro(parceiro);

        return ficha;
    }

}
