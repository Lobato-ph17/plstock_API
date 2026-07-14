package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.VendaRequest;
import com.estoque.estoque_api.model.*;
import com.estoque.estoque_api.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoService produtoService;

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    public List<Venda> listarPorProduto(Long produtoId) {
        return vendaRepository.findByProdutoIdOrderByDataVendaDesc(produtoId);
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Venda não encontrada"));
    }

    public Venda registrar(VendaRequest request) {
        Produto produto = produtoService.buscarPorId(request.getProdutoId());

        produtoService.removerDoEstoque(produto.getId(), request.getQuantidade());

        double receitaBruta = request.getPrecoUnitario() * request.getQuantidade();
        double lucroTotal = (request.getPrecoUnitario() - produto.getCustoProducao())
                            * request.getQuantidade();

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setQuantidade(request.getQuantidade());
        venda.setPrecoUnitario(request.getPrecoUnitario());
        venda.setDataVenda(LocalDateTime.now());
        venda.setReceitaBruta(receitaBruta);
        venda.setLucroTotal(lucroTotal);
        venda.setObservacao(request.getObservacao());

        return vendaRepository.save(venda);
    }
}