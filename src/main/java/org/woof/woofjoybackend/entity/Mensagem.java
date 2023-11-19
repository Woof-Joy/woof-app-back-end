package org.woof.woofjoybackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Mensagem {

    @Id
    private Integer id;
    private String mensagem;
    private LocalDate dataHora;
    private Boolean visto;
    @ManyToOne
    private Chat fkChat;
    @ManyToOne
    private Usuario fkRemetente;

    public Mensagem(String mensagem, Chat fkChat, Usuario fkRemetente) {
        this.mensagem = mensagem;
        this.dataHora = LocalDate.now();
        this.visto = false;
        this.fkChat = fkChat;
        this.fkRemetente = fkRemetente;
    }

    public Mensagem(){
        this.dataHora = LocalDate.now();
        this.visto = false;
    }
}
