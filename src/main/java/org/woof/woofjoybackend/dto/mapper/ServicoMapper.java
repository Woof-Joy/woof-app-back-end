package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.domain.entity.Cliente;
import org.woof.woofjoybackend.dto.ServicoCriacaoDTO;
import org.woof.woofjoybackend.dto.ServicoDTO;
//import org.woof.woofjoybackend.domain.entity.Dog;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.Servico;

import java.util.ArrayList;
import java.util.List;

public class ServicoMapper {
    public static ServicoDTO toDTO(Servico entidadeServico){
    if (entidadeServico == null) return null;
    ServicoDTO fichaServicoDTO = new ServicoDTO();
    fichaServicoDTO.setId(entidadeServico.getId());
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

    public static Servico toEntity (ServicoCriacaoDTO servicoCriacaoDTO, FichaServico ficha, Cliente cliente){
        Servico s = new Servico();

        s.setInicioDoServico(servicoCriacaoDTO.getInicioDoServico());
        s.setFimDoServico(servicoCriacaoDTO.getFimDoServico());
        s.setStatus(servicoCriacaoDTO.getStatus());
        s.setCliente(cliente);
        s.setFkFichaServico(ficha);

        return s;
    }

    public static ServicoCriacaoDTO toCriacaoDTO(Servico servico){
        ServicoCriacaoDTO dto = new ServicoCriacaoDTO();

        dto.setId(servico.getId());
        dto.setTipoServico(servico.getFkFichaServico().getTipoServico());
        dto.setStatus(servico.getStatus());
        dto.setInicioDoServico(servico.getInicioDoServico());
        dto.setFimDoServico(servico.getFimDoServico());
        dto.setIdParceiro(servico.getFkFichaServico().getParceiro().getUsuario().getId());
        dto.setNomeParceiro((servico.getFkFichaServico().getParceiro().getUsuario().getNome()) + " " +servico.getFkFichaServico().getParceiro().getUsuario().getSobrenome());
        dto.setIdCliente(servico.getCliente().getIdCliente());
        dto.setRelatorio(servico.getRelatorio());
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
