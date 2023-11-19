package org.woof.woofjoybackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.internal.lang.reflect.StringToType;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    @ManyToOne
    private Usuario fkRemetente;
    @ManyToOne
    private Usuario fkDestinatario;
    @OneToMany
    private List<Mensagem> mensagens;

    public Chat(String tipo, Usuario remetente, Usuario destinatario){
        this.tipo = tipo;
        this.fkRemetente = remetente;
        this.fkDestinatario = destinatario;
    }

    public String getTopico() {
        return String.format("topic/%s/%d/%d", tipo + fkRemetente.getId() + fkDestinatario.getId());
    }
}
