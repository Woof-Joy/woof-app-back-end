package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.entity.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
}
