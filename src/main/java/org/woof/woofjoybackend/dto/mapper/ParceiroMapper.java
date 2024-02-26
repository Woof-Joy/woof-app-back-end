package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.*;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.Parceiro;

import java.util.ArrayList;
import java.util.List;

public class ParceiroMapper {

    public static ParceiroDTO toDTO(Parceiro entidadeParceiro) {
        if (entidadeParceiro == null) return null;
        ParceiroDTO parceiroDTO = new ParceiroDTO();
        parceiroDTO.setIdUsuario(entidadeParceiro.getUsuario().getId());
        parceiroDTO.setIdUser(entidadeParceiro.getUsuario().getId());
        parceiroDTO.setId(entidadeParceiro.getIdParceiro());
        parceiroDTO.setNome(entidadeParceiro.getUsuario().getNome());
        parceiroDTO.setSobrenome(entidadeParceiro.getUsuario().getSobrenome());
        parceiroDTO.setEmail(entidadeParceiro.getUsuario().getEmail());
        parceiroDTO.setDataNasc(entidadeParceiro.getUsuario().getDataNasc());
        parceiroDTO.setEstrelas(entidadeParceiro.getEstrelas());
        parceiroDTO.setServicos(FichaServicoMapper.toDTO(entidadeParceiro.getServicos()));
        parceiroDTO.setDataEntrada(entidadeParceiro.getDataEntrada());
        Integer acumulador = 0;
        for (FichaServico fS :
                entidadeParceiro.getServicos()) {
            acumulador += fS.getServicos().size();
        }
        parceiroDTO.setQtdServicosPrestados(acumulador);
        return parceiroDTO;
    }

    public static List<ParceiroDTO> toDTO(List<Parceiro> listaDeEntidadeParceiro) {
        List<ParceiroDTO> listaDeParceiroDTO = new ArrayList<>();
        for (Parceiro parceiro :
                listaDeEntidadeParceiro) {
            listaDeParceiroDTO.add(ParceiroMapper.toDTO(parceiro));
        }
        return listaDeParceiroDTO;
    }

    public static ParceiroAvaliacaoFeedDTO toDTOAvaliacao(Parceiro entidadeParceiro) {
        if (entidadeParceiro == null) return null;
        ParceiroAvaliacaoFeedDTO parceiroDTO = new ParceiroAvaliacaoFeedDTO();
        parceiroDTO.setId(entidadeParceiro.getIdParceiro());
        parceiroDTO.setNome(entidadeParceiro.getUsuario().getNome());
        parceiroDTO.setSobrenome(entidadeParceiro.getUsuario().getSobrenome());
        parceiroDTO.setServicos(FichaServicoMapper.toDTOServico(entidadeParceiro.getServicos()));
        //Fazer a tratativa da avaliacao
        List<AvaliacaoDTO> avaliacoesDTO = new ArrayList<>();
        for (ParceiroFichaServicoDTO s :
                parceiroDTO.getServicos()) {
            for (ServicoDTO servicoDTO :
                    s.getServicos()) {
                avaliacoesDTO.add(servicoDTO.getAvaliacao());
            }
        }
        parceiroDTO.setAvaliacao(AvaliacaoMapper.toDouble(avaliacoesDTO));
        //Fezer a tratativa do endereco
        parceiroDTO.setEndereco(EnderecoMapper.toDTO(entidadeParceiro.getUsuario().getEndereco()));

        return parceiroDTO;
    }

    public static List<ParceiroAvaliacaoFeedDTO> toDTOAvaliacao(List<Parceiro> listaDeEntidadeParceiro) {
        List<ParceiroAvaliacaoFeedDTO> listaDeParceiroDTO = new ArrayList<>();
        for (Parceiro parceiro :
                listaDeEntidadeParceiro) {
            listaDeParceiroDTO.add(ParceiroMapper.toDTOAvaliacao(parceiro));
        }
        return listaDeParceiroDTO;
    }
}
