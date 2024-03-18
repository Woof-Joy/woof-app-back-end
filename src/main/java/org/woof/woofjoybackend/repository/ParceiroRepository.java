package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woof.woofjoybackend.domain.entity.Parceiro;
import org.woof.woofjoybackend.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;


@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Integer> {
    Boolean existsByUsuario(Usuario usuario);

    Optional<Parceiro> findByUsuarioId(Integer id);

    List<Parceiro> findByTipoServicoIgnoreCase(String tipo);

    List<Parceiro> findByUsuarioNomeContainsIgnoreCase(String nome);
}
