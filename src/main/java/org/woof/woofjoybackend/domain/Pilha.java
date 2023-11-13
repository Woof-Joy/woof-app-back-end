package org.woof.woofjoybackend.domain;

public class Pilha <T>{

    // 01) Atributos
    private T[] pilha;
    private int topo;

    // 02) Construtor
    public Pilha(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;
    }

    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {
        return topo == -1;
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        return topo == pilha.length-1;
    }

    // 05) MÃ©todo push
    public void push(T info) {
        if (!isFull()){
            topo++;
            pilha[topo] = info;
        }
        else{
            throw new IllegalStateException("Sua pilha está vazia");
        }
    }

    // 06) MÃ©todo pop
    public T pop() {
        T ultimo = pilha[topo];
        pilha[topo] = null;
        return ultimo;
    }

    // 07) MÃ©todo peek
    public T peek() {
        return isEmpty()? null : pilha[topo];
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        for (int i = topo; i >= 0 ; i--) {
            System.out.println(pilha[i]);
        }
    }


    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}