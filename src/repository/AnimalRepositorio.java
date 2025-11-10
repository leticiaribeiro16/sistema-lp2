package repository;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;
import model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalRepositorio {

    private List<Animal> animais = new ArrayList<>();

    // CREATE
    public void adicionarAnimal(Animal animal) {
        if (buscarAnimalPorIdInternal(animal.getIdAnimal()).isPresent()) {
            throw new ValidacaoException("Já existe um animal com este ID.");
        }

        if (animal.getIdade() < 0) {
            throw new ValidacaoException("Idade do animal não pode ser negativa.");
        }

        this.animais.add(animal);
    }

    // READ (todos)
    public List<Animal> listarAnimais() {
        return new ArrayList<>(this.animais);
    }

    // READ (por ID)
    public Animal buscarAnimalPorId(int id) {
        return buscarAnimalPorIdInternal(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Animal não encontrado com ID: " + id));
    }

    // Metodo auxiliar privado
    private Optional<Animal> buscarAnimalPorIdInternal(int id) {
        for (Animal a : this.animais) {
            if (a.getIdAnimal() == id) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    // UPDATE
    public void atualizarAnimal(int id, Animal animalAtualizado) {
        Animal a = buscarAnimalPorId(id);

        if (animalAtualizado.getIdade() < 0) {
            throw new ValidacaoException("Idade de atualização não pode ser negativa.");
        }

        a.setNome(animalAtualizado.getNome());
        a.setRaca(animalAtualizado.getRaca());
        a.setIdade(animalAtualizado.getIdade());
        a.setDono(animalAtualizado.getDono());
    }

    // DELETE
    public void removerAnimal(int id) {
        Animal a = buscarAnimalPorId(id);

        this.animais.remove(a);
    }
}