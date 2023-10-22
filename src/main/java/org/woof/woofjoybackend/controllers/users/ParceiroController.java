package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.service.ServiceParceiro;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/parceiros")
public class ParceiroController {
    private final ServiceParceiro serviceParceiro;
    private final ServiceUser serviceUser;

    @Autowired
    public ParceiroController(ServiceParceiro serviceParceiro, ServiceUser serviceUser) {
        this.serviceParceiro = serviceParceiro;
        this.serviceUser = serviceUser;
    }
//    MUDO AQUI

    @GetMapping()
    public ResponseEntity<List<Parceiro>> listagemParceiros() {
        List<Parceiro> listaParceiros = serviceParceiro.listaParceiros();

        if (!listaParceiros.isEmpty()) {
            return ResponseEntity.status(200).body(listaParceiros);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parceiro> listaParceiroPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceParceiro.listaParceiroPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parceiro> attParceiro(@Valid @RequestBody Parceiro parceiro, @PathVariable Integer id) {
        if (serviceParceiro.idExiste(id)) {
            return ResponseEntity.status(200).body(serviceParceiro.attParceiro(parceiro, id));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParceiro(@PathVariable Integer id) {
        if (serviceParceiro.idExiste(id)) {
            serviceParceiro.deleteParceiro(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

//    private Parceiro profissionalLogado() {
//        int index = serviceUser.indexUsuarioLogado;
//        if (index < 0) {
//            return null;
//        }
//        Parceiro parceiroLogado = (Parceiro) clientes.get(index);
//
//        return parceiroLogado;
//    }
//
//    @GetMapping("/perfil")
//    public ResponseEntity<Usuario> getPerfil() {
//        if (profissionalLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return ResponseEntity.status(200).body(profissionalLogado());
//    }
//
//    @PostMapping("/itens")
//    public ResponseEntity<Item> postItem(@RequestBody Item it) {
//        if (profissionalLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return profissionalLogado().postItem(it);
//    }
//
//    @PutMapping("/itens/{id}")
//    public ResponseEntity<Item> putItem(@RequestBody Item it, @PathVariable int id) {
//        if (profissionalLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return profissionalLogado().putItem(id, it);
//    }
//
//    @GetMapping("/itens/{id}")
//    public ResponseEntity<Item> getItemById( @PathVariable int id) {
//        if (profissionalLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return profissionalLogado().OneItemGet(id);
//    }
//
//    @GetMapping("/itens")
//    public ResponseEntity<List<Item>> getAllItens() {
//        if (profissionalLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return profissionalLogado().AllItensGet();
//    }
//
//    @DeleteMapping("/itens/{id}")
//    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
//        if (profissionalLogado() == null) {
//            return ResponseEntity.status(403).build();
//        }
//        return profissionalLogado().deleteItem(id);
//    }


}
