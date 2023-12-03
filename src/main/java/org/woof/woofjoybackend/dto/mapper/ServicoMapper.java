package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ServicoCriacaoDTO;
import org.woof.woofjoybackend.dto.ServicoDTO;
import org.woof.woofjoybackend.entity.Dog;
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

    public static Servico toEntity (ServicoCriacaoDTO servicoCriacaoDTO, List<Dog> cachorros, FichaServico ficha){
        Servico s = new Servico();

        s.setInicioDoServico(servicoCriacaoDTO.getInicioDoServico());
        s.setFimDoServico(servicoCriacaoDTO.getFimDoServico());
        s.setStatus(servicoCriacaoDTO.getStatus());
        s.setFkFichaServico(ficha);
        s.setCachorros(cachorros);

        return s;
    }

    public static ServicoCriacaoDTO toCriacaoDTO(Servico servico){
        ServicoCriacaoDTO dto = new ServicoCriacaoDTO();

        List<Integer> idCachorros = new ArrayList<>();
        for (Dog d : servico.getCachorros()){
            idCachorros.add(d.getId());
        }

        dto.setId(servico.getId());
        dto.setTipoServico(servico.getFkFichaServico().getTipoServico());
        dto.setStatus(servico.getStatus());
        dto.setIdCachorros(idCachorros);
        dto.setInicioDoServico(servico.getInicioDoServico());
        dto.setFimDoServico(servico.getFimDoServico());
        dto.setIdParceiro(servico.getFkFichaServico().getParceiro().getUsuario().getId());

        return dto;
    }

    public static List<ServicoCriacaoDTO> toCriacaoDTOList(List<Servico> servicos){
        List<ServicoCriacaoDTO> lista = new ArrayList<>();
        for (Servico servico:
                servicos) {
            lista.add(ServicoMapper.toCriacaoDTO(servico));
        }
        return lista;
    }
}
