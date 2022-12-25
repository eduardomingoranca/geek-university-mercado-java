package com.brazil.geek.model;

import static com.brazil.geek.helper.Utils.doubleToString;

public class Produto {
    private static int contador = 1;

    private final int codigo;
    private String nome;
    private Double preco;

    public Produto(String nome, Double preco) {
        this.codigo = contador;
        this.nome = nome;
        this.preco = preco;
        contador += 1;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Codigo: " + this.getCodigo() +
                "\nNome: " + this.getNome() +
                "\nPreco: " + doubleToString(this.getPreco());
    }

}
