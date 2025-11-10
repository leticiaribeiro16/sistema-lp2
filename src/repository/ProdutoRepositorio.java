package repository;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositorio {

    private List<Produto> produtos = new ArrayList<>();

    public ProdutoRepositorio() {
        this.produtos = new ArrayList<>();
    }

    // CREATE
    public void adicionarProduto(Produto produto) {
        if (buscarProdutoPorIdInternal(produto.getIdProduto()).isPresent()) {
            throw new ValidacaoException("Já existe um produto com este ID.");
        }

        if (produto.getPreco() < 0) {
            throw new ValidacaoException("O preço do produto não pode ser negativo.");
        }
        if (produto.getEstoque() < 0) {
            throw new ValidacaoException("O estoque inicial não pode ser negativo.");
        }

        this.produtos.add(produto);
    }

    // READ (todos)
    public List<Produto> listarProdutos() {
        return new ArrayList<>(this.produtos);
    }

    // READ (por ID)
    public Produto buscarProdutoPorId(int id) {
        return buscarProdutoPorIdInternal(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com ID: " + id));
    }

    private Optional<Produto> buscarProdutoPorIdInternal(int id) {
        for (Produto produto : this.produtos) {
            if (produto.getIdProduto() == id) {
                return Optional.of(produto);
            }
        }
        return Optional.empty();
    }

    // UPDATE
    public void atualizarProduto(int id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarProdutoPorId(id);

        if (produtoAtualizado.getPreco() < 0 || produtoAtualizado.getEstoque() < 0) {
            throw new ValidacaoException("Dados de atualização (preço/estoque) não podem ser negativos.");
        }

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setEstoque(produtoAtualizado.getEstoque());
    }

    // DELETE
    public void removerProduto(int id) {
        Produto produtoParaRemover = buscarProdutoPorId(id);

        this.produtos.remove(produtoParaRemover);
    }
}