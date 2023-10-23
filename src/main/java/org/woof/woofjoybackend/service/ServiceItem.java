package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.entity.object.Item;
import org.woof.woofjoybackend.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceItem {
    private final ItemRepository itemRepository;
    private final ServiceUser usuarioService;

    public Item cadastrarItem(Item it, Integer idUsuario) {
        Usuario usuario = usuarioService.listaUsuarioPorId(idUsuario);
        it.setDono(usuario);
        return itemRepository.save(it);
    }

    public Optional<Item> listaItemPorId(Integer id) {
        return itemRepository.findById(id);
    }

    public List<Item> listaItens(){
        return itemRepository.findAll();
    }

    public Item attItem(Item it, Integer idItem) {
        it.setId(idItem);
        return itemRepository.save(it);
    }

    public void deleteItem(Integer idItem) {
        itemRepository.deleteById(idItem);
    }
}