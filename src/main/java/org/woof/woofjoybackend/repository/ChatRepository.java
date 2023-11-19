package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.woof.woofjoybackend.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("SELECT c FROM Chat c WHERE c.tipo = :tipo AND c.fkRemetente.id = :idR AND c.fkDestinatario.id = :idD")
    Optional<Chat> findByArgs(String tipo, Integer idR, Integer idD);

    @Query("SELECT c FROM Chat c WHERE c.fkDestinatario.id = :idUsuario OR c.fkRemetente.id = :idUsuario")
    List<Chat> findByUser(Integer idUsuario);
}
