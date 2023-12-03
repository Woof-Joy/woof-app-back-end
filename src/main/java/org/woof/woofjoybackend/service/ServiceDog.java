package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.entity.Cliente;
import org.woof.woofjoybackend.entity.Dog;
import org.woof.woofjoybackend.repository.ClienteRepository;
import org.woof.woofjoybackend.repository.DogRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class ServiceDog {

    private DogRepository dogRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public ServiceDog(DogRepository dogRepository, ClienteRepository clienteRepository) {
        this.dogRepository = dogRepository;
        this.clienteRepository = clienteRepository;
    }


    //Criar
    public Dog criarDog(Dog dog, Integer fkdono) {
        Cliente dono = gerarFkCliente(fkdono);
        if (dono == null) {
            return null;
        }
        dog.setFkDono(dono);
        Dog dogCadastrado = dogRepository.save(dog);
        return dogCadastrado;
    }

    //Listar todos os dogs de determinado dono
    public List<Dog> listarDogs(Integer id) {
        List<Dog> dogsCadastrados = dogRepository.findAllByFkDonoUsuarioId(id);
        if (dogRepository.count() > 0) {
            return dogsCadastrados;
        }
        return new ArrayList<>();
    }


    //Listar dog especifico
    public Dog listarDog(int id) {
        return dogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    //Atulizar
    public Dog patchDog(Dog dog, int id) {

        Dog dogBanco = dogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        dogBanco.setNome(dog.getNome());
        dogBanco.setDtNasc(dog.getDtNasc());
        dogBanco.setRga(dog.getRga());
        dogBanco.setPeso(dog.getPeso());
        dogBanco.setRaca(dog.getRaca());
        dogBanco.setPorte(dog.getPorte());
        dogBanco.setConvenio(dog.getConvenio());
        dogBanco.setCarteirinha(dog.getCarteirinha());
        dogBanco.setGenero(dog.getGenero());
        dogBanco.setAgressivo(dog.getAgressivo());
        dogBanco.setDeficiencia(dog.getDeficiencia());

        return dogRepository.save(dogBanco);
    }


    //Deltar

    public boolean deletarDog(int id) {
        System.out.println("Tentando deletar...");
        if (dogRepository.existsById(id)) {
            dogRepository.deleteById(id);
            System.out.println("Cachorro deletado!");
            return true;
        }
        System.out.println("O pet não foi deletado, pois ele não existe.");
        return false;
    }

    public Cliente gerarFkCliente(Integer id) {
        return clienteRepository.findByUsuarioId(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

}
