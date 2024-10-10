package com.cep;

public class Cliente {
    private static int contador = 0; // Controla a numeração dos códigos
    private int codigo;
    private String nome;
    private Endereco endereco;
    private String telefone;

    // Construtor
    public Cliente(String nome, Endereco endereco, String telefone) {
        this.codigo = ++contador; // Incrementa o código para cada cliente
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
