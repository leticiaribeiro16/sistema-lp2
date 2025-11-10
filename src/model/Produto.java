package model;

public class Produto {

    private int idProduto;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int idProduto, String nome, double preco, int estoque) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    // Getters e Setters
    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void darBaixaEstoque(int quantidade) {
        if (quantidade <= this.estoque) {
            this.estoque -= quantidade;
        } else {
            System.out.println("Erro: Estoque insuficiente!");
        }
    }
}