package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woof.woofjoybackend.domain.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
