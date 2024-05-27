package org.woof.woofjoybackend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String topico;
    @ManyToOne
    private Usuario fkRemetente;
    @ManyToOne
    private Usuario fkDestinatario;
    @OneToMany
    private List<Mensagem> mensagens;

    public Chat(Usuario remetente, Usuario destinatario){
        this.fkRemetente = remetente;
        this.fkDestinatario = destinatario;
        this.topico = String.format("topic/%d/%d", remetente.getId(), destinatario.getId());
    }


}
