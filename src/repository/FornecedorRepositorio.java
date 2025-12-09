package repository;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;
import model.Fornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FornecedorRepositorio {

    private List<Fornecedor> fornecedores = new ArrayList<>();

    // CREATE
    public void adicionarFornecedor(Fornecedor fornecedor) {
        if (buscarPorCnpjInternal(fornecedor.getCnpj()).isPresent()) {
            throw new ValidacaoException("Já existe um fornecedor com este CNPJ.");
        }
        if (buscarPorIdInternal(fornecedor.getIdFornecedor()).isPresent()) {
            throw new ValidacaoException("Já existe um fornecedor com este ID.");
        }
        this.fornecedores.add(fornecedor);
    }

    // READ (Todos)
    public List<Fornecedor> listarFornecedores() {
        return new ArrayList<>(this.fornecedores);
    }

    // READ (Por ID)
    public Fornecedor buscarPorId(int id) {
        return buscarPorIdInternal(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado com ID: " + id));
    }

    // Auxiliares privados
    private Optional<Fornecedor> buscarPorIdInternal(int id) {
        for (Fornecedor f : fornecedores) {
            if (f.getIdFornecedor() == id) return Optional.of(f);
        }
        return Optional.empty();
    }

    private Optional<Fornecedor> buscarPorCnpjInternal(String cnpj) {
        for (Fornecedor f : fornecedores) {
            if (f.getCnpj().equals(cnpj)) return Optional.of(f);
        }
        return Optional.empty();
    }

    // UPDATE
    public void atualizarFornecedor(int id, Fornecedor novosDados) {
        Fornecedor f = buscarPorId(id);
        f.setNomeEmpresa(novosDados.getNomeEmpresa());
        f.setCnpj(novosDados.getCnpj());
        f.setTelefone(novosDados.getTelefone());
    }

    // DELETE
    public void removerFornecedor(int id) {
        Fornecedor f = buscarPorId(id);
        this.fornecedores.remove(f);
    }
}