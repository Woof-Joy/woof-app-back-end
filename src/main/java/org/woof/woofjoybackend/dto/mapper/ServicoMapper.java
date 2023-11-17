package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.FichaServicoDTO;
import org.woof.woofjoybackend.dto.ServicoDTO;
import org.woof.woofjoybackend.entity.FichaServico;
import org.woof.woofjoybackend.entity.Servico;

import java.util.ArrayList;
import java.util.List;

public class ServicoMapper {
    public static ServicoDTO toDTO(Servico entidadeServico){
    if (entidadeServico == null) return null;
    ServicoDTO fichaServicoDTO = new ServicoDTO();
    fichaServicoDTO.setId(entidadeServico.getId());
    fichaServicoDTO.setAvaliacao(AvaliacaoMapper.toDTO(entidadeServico.getAvaliacao()));
    return fichaServicoDTO;
}
    public static List<ServicoDTO> toDTO(List<Servico> listaDeEntidadeFichaServico){
        List<ServicoDTO> listaDeFichaServicoDTO = new ArrayList<>();
        for (Servico servico:
                listaDeEntidadeFichaServico) {
            listaDeFichaServicoDTO.add(ServicoMapper.toDTO(servico));
        }
        return listaDeFichaServicoDTO;
    }
}
