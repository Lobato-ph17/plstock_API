package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public List<Produto> listarSemEstoque() {
        return repository.findSemEstoque();
    }

    public List<Produto> listarEstoqueBaixo(int minimo) {
        return repository.findComEstoqueBaixo(minimo);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    public Produto criar(Produto produto) {
        if (repository.existsByNome(produto.getNome())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Já existe um produto com esse nome");
        }
        produto.setCustoProducao(0.0);
        produto.setQuantidadeEmEstoque(0);
        return repository.save(produto);
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto existente = buscarPorId(id);

        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao());
        existente.setPrecoVenda(dados.getPrecoVenda());

        return repository.save(existente);
    }

    public void atualizarCustoProducao(Long id, double novoCusto) {
        Produto produto = buscarPorId(id);
        produto.setCustoProducao(novoCusto);
        repository.save(produto);
    }

    public void adicionarAoEstoque(Long id, int quantidade) {
        Produto produto = buscarPorId(id);
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + quantidade);
        repository.save(produto);
    }

    public void removerDoEstoque(Long id, int quantidade) {
        Produto produto = buscarPorId(id);
        if (produto.getQuantidadeEmEstoque() < quantidade) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Estoque insuficiente. Disponível: " + produto.getQuantidadeEmEstoque());
        }
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);
        repository.save(produto);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}