package org.woof.woofjoybackend.dto;

import lombok.Data;
import org.woof.woofjoybackend.domain.entity.Endereco;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioMobileDTO {
    private Integer userId;
    private String nome;
    private String nomeCompleto;
    private String email;
    private String role;
    private String token;
    private String cpf;
    private String senha;
    private LocalDate dataNasc;
    private String imgUsuario;
    private String descricao;
    private ParceiroDTO parceiro;
    private ClienteDTO cliente;
    private List<ItemDTO> listaItens;
    private Endereco endereco;
}