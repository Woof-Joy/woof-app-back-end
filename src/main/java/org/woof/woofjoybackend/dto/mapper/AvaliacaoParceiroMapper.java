package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.AvaliacaoPrestadorDTO;
import org.woof.woofjoybackend.dto.ClienteDTO;
import org.woof.woofjoybackend.entity.Avaliacao;
import org.woof.woofjoybackend.entity.Cliente;

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
        avaliacaoPrestadorDTO.setTipoServico(avaliacao.getServico().getTipoServico());
        avaliacaoPrestadorDTO.setCliente(toDto(avaliacao.getServico().getCachorros().get(0).getFkDono()));

        return avaliacaoPrestadorDTO;
    }

    public static ClienteDTO toDto(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getUsuario().getNome());
        clienteDTO.setImgUsuario(cliente.getUsuario().getImgUsuario());

        return clienteDTO;
    }
}
