package org.woof.woofjoybackend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ClientePerfilDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCliente;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @Size(max = 50)
    private String sobrenome;
    @Size(min = 8, max = 8)
    private String cep;
    @Size(max = 10)
    private String numero;
    @NotBlank
    @Email
    private String email;
    private List<ClienteDogDTO> dogList;
}
