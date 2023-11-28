package org.woof.woofjoybackend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Endereco;
import org.woof.woofjoybackend.entity.Item;
import org.woof.woofjoybackend.entity.Parceiro;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioDTO {
    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String cep;
    private String numero;
    private String email;
    private String senha;
    private LocalDate dataNasc;
    private String imgUsuario;
    private String descricao;
    private ParceiroDTO parceiro;
    private ClienteDTO cliente;
    private List<Item> listaItens;
}
