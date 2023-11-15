package org.woof.woofjoybackend.dto.mapper;

import org.woof.woofjoybackend.dto.ClienteDogDTO;
import org.woof.woofjoybackend.dto.DogPerfilDTO;
import org.woof.woofjoybackend.entity.Dog;

import java.util.ArrayList;
import java.util.List;

public class DogMapper {
    public static DogPerfilDTO toDTO(Dog entidadeDog){
        if (entidadeDog == null) return null;
        DogPerfilDTO dogPerfilDTO = new DogPerfilDTO();
        dogPerfilDTO.setId(entidadeDog.getId());
        dogPerfilDTO.setNome(entidadeDog.getNome());
        dogPerfilDTO.setCarteirinha(entidadeDog.getCarteirinha());
        dogPerfilDTO.setConvenio(entidadeDog.getConvenio());
        dogPerfilDTO.setDeficiencia(entidadeDog.getDeficiencia());
        dogPerfilDTO.setAgressivo(entidadeDog.getAgressivo());
        dogPerfilDTO.setGenero(entidadeDog.getGenero());
        dogPerfilDTO.setPeso(entidadeDog.getPeso());
        dogPerfilDTO.setPorte(entidadeDog.getPorte());
        dogPerfilDTO.setRga(entidadeDog.getRga());
        dogPerfilDTO.setRaca(entidadeDog.getRaca());
        dogPerfilDTO.setDtNasc(entidadeDog.getDtNasc());
        dogPerfilDTO.setIdDono(entidadeDog.getFkDono().getIdCliente());
        dogPerfilDTO.setObservacaoList(ObservacaoMapper.toDTO(entidadeDog.getObservacaoList()));
        return dogPerfilDTO;
    }


    public static List<DogPerfilDTO> toDTO(List<Dog> listaDeDogs){
        List<DogPerfilDTO> listaDeDogsDTO = new ArrayList<>();
        for (Dog dog:
                listaDeDogs) {
            if (dog == null) continue;
            listaDeDogsDTO.add(DogMapper.toDTO(dog));
        }
        return listaDeDogsDTO;
    }

    public static ClienteDogDTO toDTOCliente(Dog entidadeDog){
        if (entidadeDog == null) return null;
        ClienteDogDTO dogDTO = new ClienteDogDTO();
        dogDTO.setId(entidadeDog.getId());
        dogDTO.setNome(entidadeDog.getNome());
        dogDTO.setDtNasc(entidadeDog.getDtNasc());
        dogDTO.setImgCachorro(entidadeDog.getImgCachorro());
        dogDTO.setIdDono(entidadeDog.getFkDono().getIdCliente());
        return dogDTO;
    }

    public static List<ClienteDogDTO> toDTOCliente(List<Dog> listaDeDogs){
        List<ClienteDogDTO> listaDeDogsDTO = new ArrayList<>();
        for (Dog dog:
                listaDeDogs) {
            if (dog == null) continue;
            listaDeDogsDTO.add(DogMapper.toDTOCliente(dog));
        }
        return listaDeDogsDTO;
    }

}
