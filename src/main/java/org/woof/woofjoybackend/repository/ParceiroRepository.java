package org.woof.woofjoybackend.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.entity.Usuario;

import java.util.List;


@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Integer>{
    Boolean existsByUsuario(Usuario usuario);
    List<Parceiro> findByTipoServicoIgnoreCase(String tipo);
    List<Parceiro> findByUsuarioNomeContainsIgnoreCase(String nome);
}
