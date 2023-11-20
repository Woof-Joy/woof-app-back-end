package org.woof.woofjoybackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.dto.DogPerfilDTO;
import org.woof.woofjoybackend.dto.ItemDTO;
import org.woof.woofjoybackend.dto.ItemFeedDTO;
import org.woof.woofjoybackend.dto.mapper.DogMapper;
import org.woof.woofjoybackend.dto.mapper.ItemMapper;
import org.woof.woofjoybackend.entity.Dog;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.entity.Item;
import org.woof.woofjoybackend.service.ServiceItem;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ServiceItem service;
    
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getItens(@PathVariable Integer id) {
        List<ItemDTO> itensCadastrados = ItemMapper.toDTO(service.listaItens());
        return itensCadastrados.isEmpty() ? ResponseEntity.noContent().build():ResponseEntity.ok().body(itensCadastrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemFeedDTO> getItem(@PathVariable Integer id) {
        ItemFeedDTO item = ItemMapper.toDTOFeed(service.listaItemPorId(id).get());
        return item == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(item);
    }

    @PostMapping("/{idDono}")
    public ResponseEntity<ItemDTO> postItem(@Valid @RequestBody Item item, @PathVariable Integer idDono) {
        ItemDTO itemCriado = ItemMapper.toDTO(service.postItem(item, idDono));
        return itemCriado == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok().body(itemCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> atulizarPet(@Valid @RequestBody Item item, @PathVariable Integer id) {
        ItemDTO itemAtualizado = ItemMapper.toDTO(service.putItem(item, id));
        return itemAtualizado == null ? ResponseEntity.notFound().build():ResponseEntity.ok().body(itemAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        Boolean dogDeletado = service.deleteItem(id);
        return dogDeletado?ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}