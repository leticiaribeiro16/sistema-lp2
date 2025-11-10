package model;

public class Cachorro extends Animal {

    private String porte;

    public Cachorro(int idAnimal, String nome, String raca, int idade, Cliente dono, String porte) {
        super(idAnimal, nome, raca, idade, dono);
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    @Override
    public String emitirSom() {
        return "Au au!";
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Porte: " + this.porte);
    }
}