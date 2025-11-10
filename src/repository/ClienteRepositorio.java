package repository;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepositorio {

    private List<Cliente> clientes;

    public ClienteRepositorio() {
        this.clientes = new ArrayList<>();
    }

    // CREATE
    public void adicionarCliente(Cliente cliente) {
        if (buscarClientePorCpfInternal(cliente.getCpf()).isPresent()) {
            throw new ValidacaoException("Já existe um cliente com este CPF.");
        }
        this.clientes.add(cliente);
    }

    // READ (todos)
    public List<Cliente> listarClientes() {
        return new ArrayList<>(this.clientes);
    }

    // READ (Ler o CPF)
    public Cliente buscarClientePorCpf(String cpf) {
        return buscarClientePorCpfInternal(cpf)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com CPF: " + cpf));
    }

    private Optional<Cliente> buscarClientePorCpfInternal(String cpf) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    // UPDATE
    public void atualizarCliente(String cpf, Cliente clienteAtualizado) {
        Cliente clienteExistente = buscarClientePorCpf(cpf);

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
    }

    // DELETE
    public void removerCliente(String cpf) {
        Cliente clienteParaRemover = buscarClientePorCpf(cpf);

        this.clientes.remove(clienteParaRemover);
    }
}