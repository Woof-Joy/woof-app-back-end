package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.object.Dog;
import org.woof.woofjoybackend.repository.DogRepository;

import java.util.List;
import java.util.Optional;

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

//    MUDO DENOVO

    //Listar
    public List<Dog> listarDogs(){
        List<Dog> dogsCadastrados = dogRepository.findAll();
        if (dogRepository.count() > 0){
            return dogsCadastrados;
        }
        return null;
    }


    public Dog listarDog(int id) {
        Optional<Dog> dogCadastrado = dogRepository.findById(id);
        System.out.println(dogCadastrado.get());
        return dogCadastrado.get();
    }
//    public Dog listarDog(int id){
//        Optional<Dog> dogCadastrado = dogRepository.findById(id);
//        if (dogCadastrado.isEmpty()){
//            return dogCadastrado.get();
//        }
//        return null;
//
//    }

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
