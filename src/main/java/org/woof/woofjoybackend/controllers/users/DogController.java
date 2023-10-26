package org.woof.woofjoybackend.controllers.users;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.entity.object.Dog;
import org.woof.woofjoybackend.domain.ListaObj;
import org.woof.woofjoybackend.entity.object.ManipuladorDeArquivo;

import org.woof.woofjoybackend.service.ServiceDog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private ServiceDog serviceDog;




    @GetMapping("dono/{id}")
    public ResponseEntity<List<Dog>> listarPet(@PathVariable Integer id) {
        List<Dog> dogsCadastrados = serviceDog.listarDogs(id);
        return dogsCadastrados == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(dogsCadastrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> listarUmPet(@PathVariable Integer id) {
        Dog dogCadastrado = serviceDog.listarDog(id);
        return dogCadastrado == null ? ResponseEntity.noContent().build():ResponseEntity.ok().body(dogCadastrado);
    }

    @PostMapping("/{idDono}")
    public ResponseEntity<Dog> cadastrarPet(@Valid @RequestBody Dog dog, @PathVariable Integer idDono) {
        Dog dogCriado = serviceDog.criarDog(dog, idDono);
        return dogCriado == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok().body(dogCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dog> atulizarPet(@PathVariable int id, @Valid @RequestBody Dog dog) {
        Dog dogAtualizado = serviceDog.atulizarDog(dog, id);
        return dogAtualizado == null ? ResponseEntity.notFound().build():ResponseEntity.ok().body(dogAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        Boolean dogDeletado = serviceDog.deletarDog(id);
        return dogDeletado?ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping(value="csv/{idDono}/download", produces = "txt/csv")
    public ResponseEntity<org.springframework.core.io.Resource> upload(@PathVariable Integer idDono) throws IOException{
        List<Dog> list = serviceDog.listarDogs(idDono);
        if (!list.isEmpty()) {
            ListaObj<Dog> listaObj = new ListaObj<Dog>(list.size());
            for (Dog dog :
                    list) {
                listaObj.adicionar(dog);
            }

            ManipuladorDeArquivo.gravaArquivoCsv(listaObj, "lista-de-cachorros");
            File csvFile = new File("lista-de-cachorros.csv");
            FileInputStream fileInputStream = new FileInputStream(csvFile);
            InputStreamResource resource =new InputStreamResource(fileInputStream);

            return ResponseEntity.ok().header("content-disposition", "attachment; filename=\"lista-de-cachorros.csv\"").body( resource);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "csv/ordenado-agressivo/{idDono}/download", produces = "txt/csv")
    public ResponseEntity<org.springframework.core.io.Resource> uploadOrdenado(@PathVariable Integer idDono) throws IOException {
        List<Dog> list = serviceDog.listarDogs(idDono);
        if (!list.isEmpty()){
            ListaObj<Dog> listaObj = new ListaObj<Dog>(list.size());
            for (Dog dog:
                list) {
                listaObj.adicionar(dog);
            }

            ManipuladorDeArquivo.gravaArquivoCsv(ordenarPorAdressividade(listaObj), "lista-de-cachorros");
            File csvFile = new File("lista-de-cachorros.csv");
            FileInputStream fileInputStream = new FileInputStream(csvFile);
            InputStreamResource resource =new InputStreamResource(fileInputStream);
            return ResponseEntity.ok().header("content-disposition", "attachment; filename=\"lista-de-cachorros.csv\"").body(resource);

        }
        return ResponseEntity.noContent().build();

    }


    public ListaObj<Dog> ordenarPorAdressividade(ListaObj<Dog> vetor){
        for (int i = 0; i < vetor.getTamanho()  ; i++) {

            int indiceMenor = i;
            for (int j = i + 1; j < vetor.getTamanho(); j++) {
                if(vetor.getElemento(j).getAgressivo() < vetor.getElemento(indiceMenor).getAgressivo()){
                    indiceMenor = j;
                }
            }
            Dog aux = vetor.getElemento(i);
            vetor.subs(vetor.getElemento(indiceMenor), i);
            vetor.subs(aux, indiceMenor);

        }
        return vetor;
    }

    @GetMapping("/{idDono}/{agressividade}")
    public ResponseEntity<Dog> buscaPetAgressivo(@PathVariable Integer agressividade, @PathVariable Integer idDono) {
        List<Dog> list = serviceDog.listarDogs(idDono);
        if (!list.isEmpty()){
            ListaObj<Dog> listaObj = new ListaObj<Dog>(list.size());
            for (Dog dog:
                    list) {
                listaObj.adicionar(dog);
            }
            Dog dog = pesquisaBinaria( listaObj, agressividade);
            return ResponseEntity.ok(dog);
        }
        return ResponseEntity.noContent().build();
    }

    public static Dog pesquisaBinaria(ListaObj<Dog> listaObj, int x){
        int iInferior = listaObj.getElemento(0).getAgressivo();
        int iSuperior = listaObj.getElemento(listaObj.getNroElem()-1).getAgressivo();

        while (iInferior < iSuperior){
            int meioVetor = (iInferior + iSuperior)/2;
            if (listaObj.getElemento(meioVetor).getAgressivo() == x){
                return listaObj.getElemento(meioVetor);
            } else if (listaObj.getElemento(meioVetor).getAgressivo() < x){
                iInferior = meioVetor + 1;
            } else {
                iSuperior = meioVetor -1;
            }
        }
        return null;
    }

}
