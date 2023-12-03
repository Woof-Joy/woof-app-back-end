package org.woof.woofjoybackend.dto;

import lombok.Data;
import org.woof.woofjoybackend.entity.Endereco;
import org.woof.woofjoybackend.entity.Item;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioDTO {
    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String senha;
    private LocalDate dataNasc;
    private String imgUsuario;
    private String descricao;
    private ParceiroDTO parceiro;
    private ClienteDTO cliente;
    private String role;
    private List<ItemDTO> listaItens;
    private Endereco endereco;
}
