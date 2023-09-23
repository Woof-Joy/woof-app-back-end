package org.woof.woofjoybackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}