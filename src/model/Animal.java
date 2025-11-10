package model;

public abstract class Animal {

    private int idAnimal;
    private String nome;
    private String raca;
    private int idade;
    private Cliente dono;

    public Animal(int idAnimal, String nome, String raca, int idade, Cliente dono) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.dono = dono;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    // Getters e Setters
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public abstract String emitirSom();

    public void exibirDetalhes() {
        System.out.println("Nome do Pet: " + this.nome);
        System.out.println("Raça: " + this.raca);
        System.out.println("Idade: " + this.idade);
        System.out.println("Dono: " + this.dono.getNome());
    }
}