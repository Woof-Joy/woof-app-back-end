package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.domain.entity.Imagem;

import java.util.Optional;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    Optional<Imagem> findByDono_IdAndTipo(Integer donoId, String tipo);
}
