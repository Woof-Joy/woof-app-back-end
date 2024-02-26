package org.woof.woofjoybackend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientePerfilDTO {
    Integer idCliente;
    Integer idUser;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String sobrenome;
    @Size(min = 8, max = 9)
    private String cep;
    @Size(max = 10)
    private String numero;
    @NotBlank
    @Email
    private String email;
    @Past
    private LocalDate dataNasc;
    private EnderecoParceiroClienteDTO endereco;
    private List<ClienteDogDTO> dogList;
}
