package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.Dog;
import org.woof.woofjoybackend.repository.DogRepository;

import java.util.List;

@Service

public class ServiceDog {

    private DogRepository dogRepository;

    @Autowired

    public ServiceDog(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    //Criar
    public Dog criarDog(Dog dog){
        Dog dogCadastrado = dogRepository.save(dog);
        return dogCadastrado;
    }


    //Listar todos os dogs
    public List<Dog> listarDogs(){
        List<Dog> dogsCadastrados = dogRepository.findAll();
        if (dogRepository.count() > 0){
            return dogsCadastrados;
        }
        return null;
    }


    //Listar dog especifico
    public Dog listarDog(int id){
        Dog dogCadastrado = dogRepository.findById(id).get();
        return dogCadastrado;
    }

    //Atulizar
    public Dog atulizarDog(Dog dog, int id){
        System.out.println("Tentando atulizar....");
        dog.setId(id);
        if (dogRepository.existsById(id)) {
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

}
