package br.com.senai.models;

public class Produto {
    // Atributos
    private String nome;
    private Double qtdEstoque;
    private String unidadeMedida;
    private Double valor;
    private CategoriaProduto categoriaProduto;
    private Double qtdMinima;

    // Construtor
    public Produto() {
    }

    public Produto(String nome, Double qtdEstoque, String unidadeMedida, Double valor, CategoriaProduto categoriaProduto, Double qtdMinima) {
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.unidadeMedida = unidadeMedida;
        this.valor = valor;
        this.categoriaProduto = categoriaProduto;
        this.qtdMinima = qtdMinima;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Double qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public Double getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Double qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    // toString
    @Override
    public String toString() {
        return "\nNome: " + nome
                + "\nQuandidade em estoque: " + qtdEstoque
                + "\nUnidade de medida: " + unidadeMedida
                + "\nValor: " + valor
                + "\nCategoria do produto: " + categoriaProduto.getNomeCategoria()
                + "\nQuantidade para alerta de reposicao: " + qtdMinima;
    }
}
