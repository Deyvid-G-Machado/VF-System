package br.com.senai.models;

public class Cliente {
    // Atributos
    private String nome;
    private String endereco;
    private Integer cep;
    private Long telefone; // Alterado para Long
    private String cpf;

    // Construtor
    public Cliente() {

    }

    public Cliente(String nome, String endereco, Integer cep, Long telefone, String cpf) {
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // toString
    @Override
    public String toString() {
        return "Nome: " + nome 
                + "\nEndereco: " + endereco 
                + "\nCEP: " + cep 
                + "\nTelefone: " + telefone 
                + "\nCPF: " + cpf;
    }
}
