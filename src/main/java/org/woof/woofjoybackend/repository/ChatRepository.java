package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.woof.woofjoybackend.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("SELECT c FROM Chat c WHERE (c.fkRemetente.id = :idR AND c.fkDestinatario.id = :idD) OR (c.fkDestinatario.id = :idR AND c.fkRemetente.id = :idD)")
    Optional<Chat> findByArgs(Integer idR, Integer idD);

    @Query("SELECT c FROM Chat c WHERE c.fkDestinatario.id = :idUsuario OR c.fkRemetente.id = :idUsuario")
    List<Chat> findByUser(Integer idUsuario);

    @Query("SELECT c.topico FROM Chat c WHERE c.fkDestinatario.id = :idUsuario OR c.fkRemetente.id = :idUsuario")
    List<String> findTopicoByUser(Integer idUsuario);
}
