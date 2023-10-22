package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Usuario;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    public Boolean existsByUsuario(Usuario usuario);
}
//    MUDO AQUI
