package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ItemDTO;
import org.woof.woofjoybackend.dto.ItemFeedDTO;
import org.woof.woofjoybackend.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    public static ItemDTO toDTO(Item entidadeItem){
        if (entidadeItem == null) return null;
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(entidadeItem.getId());
        itemDTO.setTitulo(entidadeItem.getTitulo());
        itemDTO.setDescricao(entidadeItem.getDescricao());
        itemDTO.setCategoria(entidadeItem.getCategoria());
        itemDTO.setEndereco(entidadeItem.getDono().getEndereco());
        itemDTO.setImagem(entidadeItem.getImgItemDoacao());
        return itemDTO;
    }

    public static List<ItemDTO> toDTO(List<Item> listaDeEntidadeItem){
        List<ItemDTO> listaDeItemDTO = new ArrayList<>();
        for (Item item:
                listaDeEntidadeItem) {
            listaDeItemDTO.add(ItemMapper.toDTO(item));
        }
        return listaDeItemDTO;
    }







    public static ItemFeedDTO toDTOFeed(Item entidadeItem){
        if (entidadeItem == null) return null;
        ItemFeedDTO itemDTO = new ItemFeedDTO();
        itemDTO.setNomeDoador(entidadeItem.getDono().getNome() + " " + entidadeItem.getDono().getSobrenome());
        itemDTO.setTitulo(entidadeItem.getTitulo());
        itemDTO.setDescricao(entidadeItem.getDescricao());
        itemDTO.setCategoria(entidadeItem.getCategoria());
        itemDTO.setEntrega(entidadeItem.getEntrega());
        itemDTO.setMarcaPontoEncontro(entidadeItem.getMarcaPontoEncontro());
        itemDTO.setEnviaCorreio(entidadeItem.getEnviaCorreio());
        itemDTO.setCobraTaxa(entidadeItem.getCobraTaxa());
        itemDTO.setNecessarioRetirada(entidadeItem.getNecessarioRetirada());
        itemDTO.setEndereco(EnderecoMapper.toDTO(entidadeItem.getDono().getEndereco()));
        return itemDTO;
    }

    public static List<ItemFeedDTO> toDTOFeed(List<Item> listaDeEntidadeItem){
        List<ItemFeedDTO> listaDeItemDTO = new ArrayList<>();
        for (Item item:
                listaDeEntidadeItem) {
            listaDeItemDTO.add(ItemMapper.toDTOFeed(item));
        }
        return listaDeItemDTO;
    }



}
