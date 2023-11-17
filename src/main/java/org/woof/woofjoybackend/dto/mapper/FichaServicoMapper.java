package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.FichaServicoDTO;
import org.woof.woofjoybackend.dto.ObservacaoDTO;
import org.woof.woofjoybackend.entity.FichaServico;
import org.woof.woofjoybackend.entity.Observacao;

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
}
