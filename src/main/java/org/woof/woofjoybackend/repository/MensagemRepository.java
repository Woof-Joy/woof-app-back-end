package org.woof.woofjoybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woof.woofjoybackend.domain.entity.Chat;
import org.woof.woofjoybackend.domain.entity.Mensagem;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    List<Mensagem> findAllByFkChatOrderByDataHora(Chat chat);
}