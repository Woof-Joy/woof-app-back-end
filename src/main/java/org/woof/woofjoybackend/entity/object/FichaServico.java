package org.woof.woofjoybackend.entity.object;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FichaServico {
    @Id
    private Integer id;
}
