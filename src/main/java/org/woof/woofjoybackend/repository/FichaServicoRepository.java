package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.woof.woofjoybackend.entity.FichaServico;

import java.util.List;
import java.util.Optional;

@Repository
public interface FichaServicoRepository extends JpaRepository<FichaServico, Integer> {
    @Query("SELECT f FROM FichaServico f WHERE f.parceiro.usuario.id = :idParceiro AND f.tipoServico = :tipo")
    Optional<FichaServico> findByArgs(Integer idParceiro, String tipo);

    List<FichaServico> findByParceiroUsuarioId(Integer idParceiro);

    Boolean existsByParceiroUsuarioId(Integer idParceiro);
}
