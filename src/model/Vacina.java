package model;

import java.time.LocalDate;

public class Vacina {
    private int idVacina;
    private String nomeVacina;
    private String lote;
    private LocalDate dataAplicacao;
    private Animal animal; // Associação: A vacina pertence a um animal

    public Vacina(int idVacina, String nomeVacina, String lote, LocalDate dataAplicacao, Animal animal) {
        this.idVacina = idVacina;
        this.nomeVacina = nomeVacina;
        this.lote = lote;
        this.dataAplicacao = dataAplicacao;
        this.animal = animal;
    }

    public int getIdVacina() {
        return idVacina;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}