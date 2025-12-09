package model;

// Implementa a interface
public class Servico implements IMonetario {

    private int idServico;
    private String nome;
    private double preco;
    private int duracaoEmMinutos;

    public Servico(int idServico, String nome, double preco, int duracaoEmMinutos) {
        this.idServico = idServico;
        this.nome = nome;
        this.preco = preco;
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    // Getters e Setters
    public int getIdServico() {
        return idServico;
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

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    // Implementação da Interface (Imposto de 5% para serviços - ISS)
    @Override
    public double calcularImposto() {
        return this.preco * 0.05;
    }
}