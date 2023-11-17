package org.woof.woofjoybackend.entity.response;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cep {
        @Id
        private String cep;
        private String    logradouro;
        private String    complemento;
        private String    bairro;
        private String    localidade;
        private String    uf;

}
