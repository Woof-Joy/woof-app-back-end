package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.domain.entity.Imagem;

import java.util.List;
import java.util.Optional;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    List<Imagem> findByDono_IdAndTipo(Integer donoId, String tipo);
    Optional<Imagem> findByUrlImagem(String url);
}
