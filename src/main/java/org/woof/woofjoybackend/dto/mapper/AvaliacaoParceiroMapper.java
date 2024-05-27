package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.AvaliacaoPrestadorDTO;
import org.woof.woofjoybackend.dto.ClienteAvaliacaoDTO;
import org.woof.woofjoybackend.domain.entity.Avaliacao;
import org.woof.woofjoybackend.domain.entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoParceiroMapper {

    public static List<AvaliacaoPrestadorDTO> toDtoList(List<Avaliacao> listaAvaliacao) {
        List<AvaliacaoPrestadorDTO> listaDTO = new ArrayList<>();
        for (Avaliacao a :
                listaAvaliacao) {
            listaDTO.add(toDto(a));
        }
        return listaDTO;
    }

    public static AvaliacaoPrestadorDTO toDto(Avaliacao avaliacao){
        AvaliacaoPrestadorDTO avaliacaoPrestadorDTO = new AvaliacaoPrestadorDTO();

        avaliacaoPrestadorDTO.setNota(avaliacao.getNota());
        avaliacaoPrestadorDTO.setComentario(avaliacao.getComentario());
        avaliacaoPrestadorDTO.setTipoServico(avaliacao.getFkServico().getFkFichaServico().getTipoServico());
        avaliacaoPrestadorDTO.setCliente(toDto(avaliacao.getFkServico().getCliente()));

        return avaliacaoPrestadorDTO;
    }

    public static ClienteAvaliacaoDTO toDto(Cliente cliente){
        ClienteAvaliacaoDTO clienteAvaliacaoDTO = new ClienteAvaliacaoDTO();
        clienteAvaliacaoDTO.setNome(cliente.getUsuario().getNome());
        clienteAvaliacaoDTO.setImgUsuario(cliente.getUsuario().getImgUsuario());

        return clienteAvaliacaoDTO;
    }
}
