package org.woof.woofjoybackend.entity.object;

public class Pet {
    private int id;
    private String nome;
    private String raca;
    private String porte;

    public Pet( String nome, String raca, String porte) {
        this.id =0;
        this.nome = nome;
        this.raca = raca;
        this.porte = porte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}
