package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.domain.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Boolean existsByCep(String cep);
}
