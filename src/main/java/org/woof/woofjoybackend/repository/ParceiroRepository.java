package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.entity.Usuario;


@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Integer>{
    public Boolean existsByUsuario(Usuario usuario);
}
//    MUDO AQUI
