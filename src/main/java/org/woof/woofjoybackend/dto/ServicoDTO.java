package org.woof.woofjoybackend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.woof.woofjoybackend.entity.Avaliacao;
import org.woof.woofjoybackend.entity.Relatorio;

import java.time.LocalDate;

@Data
public class ServicoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private AvaliacaoDTO avaliacao;
}
