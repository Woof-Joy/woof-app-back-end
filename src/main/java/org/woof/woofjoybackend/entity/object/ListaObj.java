package org.woof.woofjoybackend.entity.object;

import java.util.Objects;

public class ListaObj <T>{

    private T[] vetor;


    private int nroElem;


    ListaObj(int tamnhoVetor){
        vetor = (T[]) new Object[tamnhoVetor];
        nroElem = 0;
    }
    
    public void adiciona(T n){
        if (Objects.equals(nroElem, vetor.length)){
            throw new IllegalStateException("Sua lista está cheia");
        }
        vetor[nroElem] = n;
        nroElem++;
    }

    public int busca(T elem){
        for (int j = 0; j < nroElem; j++) {
            if (Objects.equals(elem, vetor[j])){
                return j;
            }
        } return -1;
    }


    public boolean removePeloIndice(int i){
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


    public boolean removeElemento(T elem){
        return removePeloIndice(busca(elem));
    }

    public void limpa() {
        int tamanho = vetor.length;
        vetor = (T[]) new Object[tamanho];
        nroElem =0;
    }


    public void exibe(){
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
