package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Dog criarDog(Dog dog, Integer fkdono){
        Cliente dono = gerarFkCliente(fkdono);
        if (dono == null){
            return null;
        }
        dog.setFkDono(dono);
        Dog dogCadastrado = dogRepository.save(dog);
        return dogCadastrado;
    }


    //Listar todos os dogs de determinado dono
    public List<Dog> listarDogs(Integer id){
        List<Dog> dogsCadastrados = dogRepository.findDogsByOwnerId(id);
        if (dogRepository.count() > 0){
            return dogsCadastrados;
        }
        return new ArrayList<>();
    }


    //Listar dog especifico
    public Dog listarDog(int id){
        Dog dogCadastrado = dogRepository.findById(id).get();
        return dogCadastrado;
    }

    //Atulizar
    public Dog atulizarDog(Dog dog, int id){
        System.out.println("Tentando atulizar....");
        if (dogRepository.existsById(id)) {
            dog.setId(id);
            Dog dogAtualizado = dogRepository.save(dog);
            System.out.println("Cachorro atulizado!");
            return dogAtualizado;
        }
        System.out.println("O pet não foi atulizado, pois ele não existe.");
        return null;
    }


    //Deltar

    public boolean deletarDog(int id){
        System.out.println("Tentando deletar...");
        if (dogRepository.existsById(id)) {
            dogRepository.deleteById(id);
            System.out.println("Cachorro deletado!");
            return true;
        }
        System.out.println("O pet não foi deletado, pois ele não existe.");
        return false;
    }

    public Cliente gerarFkCliente (Integer id){
        Cliente cliente = clienteRepository.findById(id).get();
        return cliente;
    }

}
