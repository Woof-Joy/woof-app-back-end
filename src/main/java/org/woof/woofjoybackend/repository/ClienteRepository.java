package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woof.woofjoybackend.domain.entity.Cliente;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    public Optional<Cliente> findByUsuarioId(Integer id);
}
