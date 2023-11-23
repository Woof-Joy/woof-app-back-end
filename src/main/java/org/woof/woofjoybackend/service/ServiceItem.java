package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Dog;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.entity.Item;
import org.woof.woofjoybackend.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceItem {
    private final ItemRepository itemRepository;
    private final ServiceUser usuarioService;

    public Item postItem(Item it, Integer idUsuario) {
        Usuario dono = usuarioService.listaUsuarioPorId(idUsuario);
        if (dono == null){
            return null;
        }
        it.setDono(dono);
        Item itemCadastrado = itemRepository.save(it);
        return itemCadastrado;
    }

    public Optional<Item> listaItemPorId(Integer id) {
        return itemRepository.findById(id);
    }

    public List<Item> listaItens(){
        return itemRepository.findAll();
    }

    public Item putItem(Item it, Integer idItem) {
        System.out.println("Tentando atulizar....");
        if (itemRepository.existsById(idItem)) {
            it.setId(idItem);
            Item itAtualizado = itemRepository.save(it);
            System.out.println("Item atulizado!");
            return itAtualizado;
        }
        System.out.println("O item não foi atulizado, pois ele não existe.");
        return null;
    }

    public Boolean deleteItem(Integer idItem) {
        System.out.println("Tentando deletar...");
        if (itemRepository.existsById(idItem)) {
            itemRepository.deleteById(idItem);
            System.out.println("Item deletado!");
            return true;
        }
        System.out.println("O item não foi deletado, pois ele não existe.");
        return false;
    }

}