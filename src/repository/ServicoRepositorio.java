package repository;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;
import model.Servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoRepositorio {

    private List<Servico> servicos = new ArrayList<>();

    // CREATE
    public void adicionarServico(Servico servico) {
        if (buscarServicoPorIdInternal(servico.getIdServico()).isPresent()) {
            throw new ValidacaoException("Já existe um serviço com este ID.");
        }

        if (servico.getPreco() < 0) {
            throw new ValidacaoException("Preço do serviço não pode ser negativo.");
        }
        if (servico.getDuracaoEmMinutos() <= 0) {
            throw new ValidacaoException("Duração do serviço deve ser maior que zero.");
        }

        this.servicos.add(servico);
    }

    // READ (todos)
    public List<Servico> listarServicos() {
        return new ArrayList<>(this.servicos);
    }

    // READ (por ID)
    public Servico buscarServicoPorId(int id) {
        return buscarServicoPorIdInternal(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Serviço não encontrado com ID: " + id));
    }

    private Optional<Servico> buscarServicoPorIdInternal(int id) {
        for (Servico s : this.servicos) {
            if (s.getIdServico() == id) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    // UPDATE
    public void atualizarServico(int id, Servico servicoAtualizado) {
        Servico s = buscarServicoPorId(id);

        if (servicoAtualizado.getPreco() < 0 || servicoAtualizado.getDuracaoEmMinutos() <= 0) {
            throw new ValidacaoException("Dados de atualização (preço/duração) inválidos.");
        }

        s.setNome(servicoAtualizado.getNome());
        s.setPreco(servicoAtualizado.getPreco());
        s.setDuracaoEmMinutos(servicoAtualizado.getDuracaoEmMinutos());
    }

    // DELETE
    public void removerServico(int id) {
        Servico s = buscarServicoPorId(id);

        this.servicos.remove(s);
    }
}