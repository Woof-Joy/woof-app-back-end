package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.domain.entity.Item;
import org.woof.woofjoybackend.repository.ItemRepository;
import org.woof.woofjoybackend.service.users.ServiceUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceItem {
    private final ItemRepository itemRepository;
    private final ServiceUser usuarioService;

    public Item postItem(Item it, Integer idUsuario) {
        it.setDono(usuarioService.listaUsuarioPorId(idUsuario));
        return itemRepository.save(it);
    }

    public Item listaItemPorId(Integer id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public List<Item> findByDono(Integer id) {
        return itemRepository.findAllByDonoId(id);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item patchItem(Item it, Integer idItem) {

        Item itemBanco = itemRepository.findById(idItem).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        if (it.getTitulo() != null) itemBanco.setTitulo(it.getTitulo());
        if (it.getImgItemDoacao() != null) itemBanco.setImgItemDoacao(it.getImgItemDoacao());
        if (it.getCategoria() != null) itemBanco.setCategoria(it.getCategoria());
        if (it.getEstado() != null) itemBanco.setEstado(it.getEstado());
        if (it.getDescricao() != null) itemBanco.setDescricao(it.getDescricao());
        if (it.getEntrega() != null) itemBanco.setEntrega(it.getEntrega());
        if (it.getMarcaPontoEncontro() != null) itemBanco.setMarcaPontoEncontro(it.getMarcaPontoEncontro());
        if (it.getEnviaCorreio() != null) itemBanco.setEnviaCorreio(it.getEnviaCorreio());
        if (it.getCobraTaxa() != null) itemBanco.setCobraTaxa(it.getCobraTaxa());
        if (it.getNecessarioRetirada() != null) itemBanco.setNecessarioRetirada(it.getNecessarioRetirada());

        return itemRepository.save(itemBanco);
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