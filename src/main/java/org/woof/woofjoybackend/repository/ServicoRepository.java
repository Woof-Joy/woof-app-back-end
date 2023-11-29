package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
