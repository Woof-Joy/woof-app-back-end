package org.woof.woofjoybackend.domain;

import org.woof.woofjoybackend.entity.object.Dog;

import java.util.Objects;

public class ListaObj <T>{

    private T[] vetor;


    private int nroElem;


    public ListaObj(int tamnhoVetor){
        vetor = (T[]) new Object[tamnhoVetor];
        nroElem = 0;
    }

    public void adicionar(T n){
        if (Objects.equals(nroElem, vetor.length)){
            throw new IllegalStateException("Sua lista está cheia");
        }
        vetor[nroElem] = n;
        nroElem++;
    }

    public void adicionar(T n, int i){
        if (Objects.equals(nroElem, vetor.length)){
            throw new IllegalStateException("Sua lista está cheia");
        }
        for (int j = nroElem; j > i; j++) {
            vetor[j] = vetor[j+1];
        }

        vetor[i] = n;
        nroElem++;
    }

    public int buscar(T elem){
        for (int j = 0; j < nroElem; j++) {
            if (Objects.equals(elem, vetor[j])){
                return j;
            }
        } return -1;
    }


    public boolean removerPeloIndice(int i){
        if (i >= 0 && i < nroElem){
            nroElem--;
            int j = i;
            while(j < nroElem){
                vetor[j] = vetor[j + 1];
                j++;
            }
            return true;
        }
        return false;
    }


    public boolean removerElemento(T elem){
        return removerPeloIndice(buscar(elem));
    }

    public void limpa() {
        int tamanho = vetor.length;
        vetor = (T[]) new Object[tamanho];
        nroElem =0;
    }

    public static ListaObj<Dog> ordenarPorAdressividade(ListaObj<Dog> vetor){
        for (int i = 0; i < vetor.getTamanho()  ; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < vetor.getTamanho(); j++) {
                if(vetor.getElemento(j).getAgressivo() < vetor.getElemento(indiceMenor).getAgressivo()){
                    indiceMenor = j;
                }
            }
            Dog aux = vetor.getElemento(i);
            vetor.adicionar(vetor.getElemento(indiceMenor), i);
            vetor.adicionar(aux, indiceMenor);
        }
        return vetor;
    }


    public void exibir(){
        for (T i:vetor){
            System.out.println(i);
        }
    }


    public int getNroElem() {
        return nroElem;
    }

    public T[] getVetor() {
        return vetor;
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        return indice >= 0 && indice < nroElem ? vetor[indice] : null;
    }


}
