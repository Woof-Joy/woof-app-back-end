package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.UsuarioChatDTO;
import org.woof.woofjoybackend.dto.UsuarioDTO;
import org.woof.woofjoybackend.entity.Usuario;

import java.util.ArrayList;
import java.util.List;


public class UsuarioMapper {
    public static List<UsuarioDTO> toDtoList(List<Usuario> listaUsuario) {
        List<UsuarioDTO> listaDto = new ArrayList<>();

        for (Usuario u : listaUsuario) {
            listaDto.add(toDto(u));
        }

        return listaDto;
    }

    public static UsuarioChatDTO toDtoChat(Usuario usuario) {
        UsuarioChatDTO dto = new UsuarioChatDTO();

        dto.setId(usuario.getId());
        dto.setNomeCompleto(String.format("%s %s", usuario.getNome(), usuario.getSobrenome()));
        dto.setImgUsuario(usuario.getImgUsuario());

        return dto;
    }

    public static UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNomeCompleto(String.format("%s %s", usuario.getNome(), usuario.getSobrenome()));
        dto.setImgUsuario(usuario.getImgUsuario());
        dto.setCpf(usuario.getCpf());
        dto.setCep(usuario.getCep());
        dto.setNumero(usuario.getNumero());
        dto.setCliente(ClienteMapper.toDTO(usuario.getCliente().orElse(null)));
        dto.setParceiro(ParceiroMapper.toDTO(usuario.getParceiro().orElse(null)));
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        dto.setDescricao(usuario.getDescricao());
        dto.setDataNasc(usuario.getDataNasc());
        dto.setListaItens(usuario.getListaItens());

        return dto;
    }
}


