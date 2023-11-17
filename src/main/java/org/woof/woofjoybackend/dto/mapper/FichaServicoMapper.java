package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.FichaServicoDTO;
import org.woof.woofjoybackend.dto.ParceiroFichaServicoDTO;
import org.woof.woofjoybackend.dto.ServicoDTO;
import org.woof.woofjoybackend.entity.FichaServico;
import org.woof.woofjoybackend.entity.Servico;
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
        ParceiroFichaServicoDTO fichaServicoDTO = new ParceiroFichaServicoDTO();
        fichaServicoDTO.setTipoServico(entidadeFichaServico.getTipoServico());
        fichaServicoDTO.setValor(entidadeFichaServico.getValor());
        List<ServicoDTO> servicosDTOS = new ArrayList<>();
        for (Servico fichaServico:
             entidadeFichaServico.getServicos()) {
            servicosDTOS.add(ServicoMapper.toDTO(fichaServico));
        }
        return fichaServicoDTO;
    }


    public static List<ParceiroFichaServicoDTO> toDTOServico(List<FichaServico> listaDeEntidadeFichaServico){
        List<ParceiroFichaServicoDTO> listaDeFichaServicoDTO = new ArrayList<>();
        for (FichaServico servico:
                listaDeEntidadeFichaServico) {
            listaDeFichaServicoDTO.add(FichaServicoMapper.toDTOServico(servico));
        }
        return listaDeFichaServicoDTO;
    }
}
