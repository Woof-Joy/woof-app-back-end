package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mensagem;
    private LocalDateTime dataHora;
    private Boolean visto;
    @ManyToOne
    private Chat fkChat;
    @ManyToOne
    private Usuario fkRemetente;

    public Mensagem(String mensagem, Chat fkChat, Usuario fkRemetente) {
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
        this.visto = false;
        this.fkChat = fkChat;
        this.fkRemetente = fkRemetente;
    }

    public Mensagem(){
        this.dataHora = LocalDateTime.now();
        this.visto = false;
    }
}
