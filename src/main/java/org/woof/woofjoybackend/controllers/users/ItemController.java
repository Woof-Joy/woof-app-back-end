package org.woof.woofjoybackend.controllers.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.entity.object.Item;
import org.woof.woofjoybackend.service.ServiceItem;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ServiceItem service;
    private final ServiceUser serviceUser;


    @PostMapping("/{idUsuario}")
    public ResponseEntity<Item> cadastrarItem(@Valid @RequestBody Item item, @PathVariable Integer idUsuario) {
        if (serviceUser.existsById(idUsuario)) {
            service.cadastrarItem(item, idUsuario);
            return ResponseEntity.status(201).body(item);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{idUsuario}/{idItem}")
    public ResponseEntity<Item> listaItem(@PathVariable Integer idUsuario, @PathVariable Integer idItem) {
        Usuario usuario = serviceUser.listaUsuarioPorId(idUsuario);
        Item item = service.listaItemPorId(idItem).get();

        if (item != null) {
            return ResponseEntity.status(200).body(item);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<Item>> listaItensUsuario(@PathVariable Integer idUsuario) {
        Usuario usuario = serviceUser.listaUsuarioPorId(idUsuario);
        List<Item> listaItens = usuario.getListaItens();

        if (!listaItens.isEmpty()) {
            return ResponseEntity.status(200).body(listaItens);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<List<Item>> listaTodosItens() {
        List<Usuario> listaUsuarios = serviceUser.listaUsuarios();
        List<Item> listaItens = new ArrayList<>();

        for (Usuario u : listaUsuarios) {
            listaItens.addAll(u.getListaItens());
        }

        if (!listaItens.isEmpty()) {
            return ResponseEntity.status(200).body(listaItens);
        }
        return ResponseEntity.status(204).build();
    }


    @PutMapping("/{idUsuario}/{idItem}")
    public ResponseEntity<Item> attItem(@Valid @RequestBody Item it, @PathVariable Integer idUsuario, @PathVariable Integer idItem) {
        if (serviceUser.existsById(idUsuario) && service.listaItemPorId(idItem).isPresent()) {
            return ResponseEntity.status(200).body(service.attItem(it, idItem));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{idUsuario}/{idItem}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer idUsuario, @PathVariable Integer idItem) {
        Usuario usuario = serviceUser.listaUsuarioPorId(idUsuario);
        if (service.listaItemPorId(idItem).isPresent()) {
            service.deleteItem(idItem);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}