package repository;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;
import model.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioRepositorio {

    private List<Funcionario> funcionarios = new ArrayList<>();

    // CREATE
    public void adicionarFuncionario(Funcionario funcionario) {
        if (buscarFuncionarioPorCpfInternal(funcionario.getCpf()).isPresent()) {
            throw new ValidacaoException("Já existe um funcionário com este CPF.");
        }

        if (funcionario.getSalario() < 0) {
            throw new ValidacaoException("Salário não pode ser negativo.");
        }

        this.funcionarios.add(funcionario);
    }

    // READ (todos)
    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(this.funcionarios);
    }

    // READ (por CPF)
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        return buscarFuncionarioPorCpfInternal(cpf)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado com CPF: " + cpf));
    }

    private Optional<Funcionario> buscarFuncionarioPorCpfInternal(String cpf) {
        for (Funcionario f : this.funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }

    // UPDATE
    public void atualizarFuncionario(String cpf, Funcionario funcionarioAtualizado) {
        Funcionario f = buscarFuncionarioPorCpf(cpf);

        if (funcionarioAtualizado.getSalario() < 0) {
            throw new ValidacaoException("Salário de atualização não pode ser negativo.");
        }

        f.setNome(funcionarioAtualizado.getNome());
        f.setTelefone(funcionarioAtualizado.getTelefone());
        f.setCargo(funcionarioAtualizado.getCargo());
        f.setSalario(funcionarioAtualizado.getSalario());
    }

    // DELETE
    public void removerFuncionario(String cpf) {
        Funcionario f = buscarFuncionarioPorCpf(cpf);

        this.funcionarios.remove(f);
    }
}