package org.woof.woofjoybackend.chat.websocket.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String message;
    private Integer idRemetente;
    private Integer idDestinatario;
    @Pattern(regexp = "^(doacao|servico)$", message = "O tipo deve ser 'doacao' ou 'servico'")
    private String tipo;
}
