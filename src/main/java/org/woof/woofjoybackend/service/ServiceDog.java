package org.woof.woofjoybackend.service;

import org.woof.woofjoybackend.entity.object.Dog;
import org.woof.woofjoybackend.repository.DogRepository;

import java.util.List;
import java.util.Optional;

public class ServiceDog {

    private DogRepository dogRepository;

    public ServiceDog(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    //Criar
    public Dog criarDog(Dog dog){
        Dog dogCadastrado = dogRepository.save(dog);
        return dogCadastrado;
    }


    //Listar
    public List<Dog> listarDogs(Dog dog){
        List<Dog> dogCadastrado = dogRepository.findAll();
        return dogCadastrado;
    }

    public Dog listarDog(Dog dog){
        Optional<Dog> dogCadastrado = dogRepository.findById(dog.getId());
        System.out.println(dogCadastrado.get());
        return dogCadastrado.get();
    }

    //Atulizar
    public Dog atulizarDog(Dog dog, int id){
        System.out.println("Tentando cadastrar....");
        dog.setId(id);
        if (dogRepository.existsById(dog.getId())) {
            Dog dogAtualizado = dogRepository.save(dog);
            System.out.println("Cachorro atulizado!");
            return dogAtualizado;
        }
        System.out.println("O pet não foi atulizado, pois ele não existe.");
        return null;
    }


    //Deltar

    public void deletarDog(int id){
        System.out.println("Tentando deletar...");
        if (dogRepository.existsById(id)) {
            dogRepository.deleteById(id);
            System.out.println("Cachorro deletado!");
            return ;
        }
        System.out.println("O pet não foi deletado, pois ele não existe.");
    }

}
