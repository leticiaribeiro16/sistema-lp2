package repository;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;
import model.Agendamento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendamentoRepositorio {

    private List<Agendamento> agendamentos = new ArrayList<>();

    // CREATE
    public void adicionarAgendamento(Agendamento agendamento) {
        if (buscarAgendamentoPorIdInternal(agendamento.getIdAgendamento()).isPresent()) {
            throw new ValidacaoException("Já existe um agendamento com este ID.");
        }

        if (agendamento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new ValidacaoException("Não é possível criar agendamentos em datas passadas.");
        }

        this.agendamentos.add(agendamento);
    }

    // READ (todos)
    public List<Agendamento> listarAgendamentos() {
        return new ArrayList<>(this.agendamentos);
    }

    // READ (por ID)
    public Agendamento buscarAgendamentoPorId(int id) {
        return buscarAgendamentoPorIdInternal(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado com ID: " + id));
    }

    // Metodo auxiliar privado
    private Optional<Agendamento> buscarAgendamentoPorIdInternal(int id) {
        for (Agendamento a : this.agendamentos) {
            if (a.getIdAgendamento() == id) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    // UPDATE (mudar a data/hora)
    public void atualizarAgendamento(int id, Agendamento agendamentoAtualizado) {
        Agendamento a = buscarAgendamentoPorId(id);

        if (agendamentoAtualizado.getDataHora().isBefore(LocalDateTime.now())) {
            throw new ValidacaoException("Não é possível reagendar para datas passadas.");
        }

        a.setDataHora(agendamentoAtualizado.getDataHora());
    }

    // UPDATE (mais específico)
    public void marcarAgendamentoConcluido(int id) {
        Agendamento a = buscarAgendamentoPorId(id);

        a.marcarConcluido();
    }

    // DELETE
    public void removerAgendamento(int id) {
        Agendamento a = buscarAgendamentoPorId(id);

        this.agendamentos.remove(a);
    }
}