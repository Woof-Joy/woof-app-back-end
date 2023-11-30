package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.entity.DonoImagem;
import org.woof.woofjoybackend.entity.Imagem;

public interface DonoImagemRepository extends JpaRepository<DonoImagem, Integer> {
}
