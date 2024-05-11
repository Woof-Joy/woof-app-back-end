package org.woof.woofjoybackend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.woof.woofjoybackend.domain.entity.DonoImagem;

@Data
public class ImagemDTO {
    private Integer id;
    @Size(min = 3)
    private String urlImagem;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "dono")
    private DonoImagem dono;
}
