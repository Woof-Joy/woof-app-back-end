package org.woof.woofjoybackend.domain;


import java.util.Objects;

public class ListaObj <T>{

    private T[] vetor;


    private int nroElem;


    public ListaObj(int tamnhoVetor){
        vetor = (T[]) new Object[tamnhoVetor];
        nroElem = 0;
    }

    public void adicionar(T n){
        if (nroElem > vetor.length-1){
            throw new IllegalStateException("Sua lista está cheia");
        }
        vetor[nroElem] = n;
        nroElem++;
    }

    public void subs(T n, int i){
        if (i >= 0 && i < nroElem) {
            vetor[i] = n;
        }else {
            System.out.println("Indice inválido");
        }
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
