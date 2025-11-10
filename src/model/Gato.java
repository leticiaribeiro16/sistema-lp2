package model;

public class Gato extends Animal {

    private boolean pelagemLonga;

    public Gato(int idAnimal, String nome, String raca, int idade, Cliente dono, boolean pelagemLonga) {
        super(idAnimal, nome, raca, idade, dono);
        this.pelagemLonga = pelagemLonga;
    }

    public boolean isPelagemLonga() {
        return pelagemLonga;
    }

    public void setPelagemLonga(boolean pelagemLonga) {
        this.pelagemLonga = pelagemLonga;
    }

    @Override
    public String emitirSom() {
        return "Miau!";
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Chama o método da classe-mãe
        System.out.println("Pelagem Longa: " + (this.pelagemLonga ? "Sim" : "Não"));
    }
}