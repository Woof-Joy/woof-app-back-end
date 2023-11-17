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
    public Boolean existsByUsuario(Usuario usuario);

    @Query("SELECT DISTINCT p FROM Parceiro p JOIN p.servicos fKFichaServico JOIN fKFichaServico.servicos fKServicos JOIN fKServicos.avaliacao a WHERE p.idParceiro = :id ORDER BY a.nota ASC")
    public List<Parceiro> findParceiroByAvaliacoesOrderByNotaAsc(@Param("id") Integer id);

    @Query("SELECT DISTINCT p FROM Parceiro p JOIN p.servicos fKFichaServico JOIN fKFichaServico.servicos fKServicos JOIN fKServicos.avaliacao a WHERE p.idParceiro = :id ORDER BY a.nota DESC")
    public List<Parceiro> findParceiroByAvaliacoesOrderByNotaDesc(@Param("id") Integer id);
}
