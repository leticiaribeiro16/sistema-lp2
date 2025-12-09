package repository;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;
import model.Vacina;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VacinaRepositorio {

    private List<Vacina> vacinas = new ArrayList<>();

    // CREATE
    public void adicionarVacina(Vacina vacina) {
        if (buscarPorIdInternal(vacina.getIdVacina()).isPresent()) {
            throw new ValidacaoException("Já existe uma vacina registrada com este ID.");
        }
        if (vacina.getDataAplicacao().isAfter(LocalDate.now())) {
            throw new ValidacaoException("A data de aplicação não pode ser futura.");
        }
        this.vacinas.add(vacina);
    }

    // READ (Todas)
    public List<Vacina> listarVacinas() {
        return new ArrayList<>(this.vacinas);
    }

    // READ (Por ID)
    public Vacina buscarPorId(int id) {
        return buscarPorIdInternal(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Vacina não encontrada com ID: " + id));
    }

    private Optional<Vacina> buscarPorIdInternal(int id) {
        for (Vacina v : vacinas) {
            if (v.getIdVacina() == id) return Optional.of(v);
        }
        return Optional.empty();
    }

    // UPDATE
    public void atualizarVacina(int id, Vacina novosDados) {
        Vacina v = buscarPorId(id);
        if (novosDados.getDataAplicacao().isAfter(LocalDate.now())) {
            throw new ValidacaoException("A data de aplicação não pode ser futura.");
        }
        v.setNomeVacina(novosDados.getNomeVacina());
        v.setLote(novosDados.getLote());
        v.setDataAplicacao(novosDados.getDataAplicacao());
    }

    // DELETE
    public void removerVacina(int id) {
        Vacina v = buscarPorId(id);
        this.vacinas.remove(v);
    }
}