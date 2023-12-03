package org.woof.woofjoybackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.ItemDTO;
import org.woof.woofjoybackend.dto.mapper.ItemMapper;
import org.woof.woofjoybackend.entity.Item;
import org.woof.woofjoybackend.service.ServiceItem;

import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ServiceItem service;

    @GetMapping("/dono/{id}")
    public ResponseEntity<List<ItemDTO>> findByDono(@PathVariable Integer id) {
        List<Item> lista = service.findByDono(id);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ItemMapper.toDTO(lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(ItemMapper.toDTO(service.listaItemPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAll(){
        List<Item> lista = service.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ItemMapper.toDTO(lista));
    }

    @PostMapping("/{idDono}")
    public ResponseEntity<ItemDTO> post(@Valid @RequestBody Item item, @PathVariable Integer idDono) {
        return ResponseEntity.created(null).body(ItemMapper.toDTO(service.postItem(item, idDono)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDTO> patch(@Valid @RequestBody Item item, @PathVariable Integer id) {
        return ResponseEntity.ok(ItemMapper.toDTO(service.patchItem(item, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Boolean dogDeletado = service.deleteItem(id);
        return dogDeletado?ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}