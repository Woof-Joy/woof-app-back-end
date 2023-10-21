package org.woof.woofjoybackend.entity.object;

public class Item {
    private Integer id;
    private String nome;
    private String img;
    private String descricao;

    public Item(Integer id, String nome, String img, String descricao) {
        this.id = id;
        this.nome = nome;
        this.img = img;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
