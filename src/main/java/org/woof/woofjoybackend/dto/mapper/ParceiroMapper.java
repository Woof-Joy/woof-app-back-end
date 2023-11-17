package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ObservacaoDTO;
import org.woof.woofjoybackend.dto.ParceiroDTO;
import org.woof.woofjoybackend.entity.Observacao;
import org.woof.woofjoybackend.entity.Parceiro;

import java.util.ArrayList;
import java.util.List;

public class ParceiroMapper {

    public static ParceiroDTO toDTO(Parceiro entidadeParceiro){
        if (entidadeParceiro == null) return null;
        ParceiroDTO parceiroDTO = new ParceiroDTO();
        parceiroDTO.setNome(entidadeParceiro.getUsuario().getNome());
        parceiroDTO.setSobrenome(entidadeParceiro.getUsuario().getSobrenome());
        parceiroDTO.setCep(entidadeParceiro.getUsuario().getCep());
        parceiroDTO.setEmail(entidadeParceiro.getUsuario().getEmail());
        parceiroDTO.setDataNasc(entidadeParceiro.getUsuario().getDataNasc());
        parceiroDTO.setServicos(FichaServicoMapper.toDTO(entidadeParceiro.getServicos()));
        return parceiroDTO;
    }

    public static List<ParceiroDTO> toDTO(List<Parceiro> listaDeEntidadeParceiro){
        List<ParceiroDTO> listaDeParceiroDTO = new ArrayList<>();
        for (Parceiro parceiro:
                listaDeEntidadeParceiro) {
            listaDeParceiroDTO.add(ParceiroMapper.toDTO(parceiro));
        }
        return listaDeParceiroDTO;
    }
}
