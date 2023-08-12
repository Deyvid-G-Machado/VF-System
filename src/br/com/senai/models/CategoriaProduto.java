package br.com.senai.models;

public class CategoriaProduto {
    private String nomeCategoria;

    public CategoriaProduto() {
    }

    public CategoriaProduto(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public String toString() {
        return "Tipo de produto: " + nomeCategoria;
    }
}
