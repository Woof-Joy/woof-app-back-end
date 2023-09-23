package org.woof.woofjoybackend.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.entity.object.Item;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ParceiroController {
    private final ServiceUser serviceUser;
    private final List<Usuario> clientes;

    @Autowired
    public ParceiroController(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
        this.clientes = serviceUser.getClientes();
    }

    private Parceiro profissionalLogado() {
        int index = serviceUser.indexUsuarioLogado;
        if (index < 0) {
            return null;
        }
        Parceiro parceiroLogado = (Parceiro) clientes.get(index);

        return parceiroLogado;
    }

    @GetMapping("/perfil")
    public ResponseEntity<Usuario> getPerfil() {
        if (profissionalLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.status(200).body(profissionalLogado());
    }

    @PostMapping("/itens")
    public ResponseEntity<Item> postItem(@RequestBody Item it) {
        if (profissionalLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return profissionalLogado().postItem(it);
    }

    @PutMapping("/itens/{id}")
    public ResponseEntity<Item> putItem(@RequestBody Item it, @PathVariable int id) {
        if (profissionalLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return profissionalLogado().putItem(id, it);
    }

    @GetMapping("/itens/{id}")
    public ResponseEntity<Item> getItemById( @PathVariable int id) {
        if (profissionalLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return profissionalLogado().OneItemGet(id);
    }

    @GetMapping("/itens")
    public ResponseEntity<List<Item>> getAllItens() {
        if (profissionalLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return profissionalLogado().AllItensGet();
    }

    @DeleteMapping("/itens/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        if (profissionalLogado() == null) {
            return ResponseEntity.status(403).build();
        }
        return profissionalLogado().deleteItem(id);
    }
    

}
