package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.domain.entity.Observacao;

public interface ObservacaoRepository extends JpaRepository<Observacao, Integer> {

}
