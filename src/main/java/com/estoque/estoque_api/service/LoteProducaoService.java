package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.LoteProducaoRequest;
import com.estoque.estoque_api.model.*;
import com.estoque.estoque_api.repository.LoteProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoteProducaoService {

    @Autowired
    private LoteProducaoRepository loteRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    public List<LoteProducao> listarTodos() {
        return loteRepository.findAll();
    }

    public List<LoteProducao> listarPorProduto(Long produtoId) {
        return loteRepository.findByProdutoIdOrderByDataProducaoDesc(produtoId);
    }

    public LoteProducao registrar(LoteProducaoRequest request) {
        Produto produto = produtoService.buscarPorId(request.getProdutoId());

        Receita receita = receitaService.buscarPorProduto(produto.getId());

        for (ItemReceita item : receita.getItens()) {
            double necessario = item.getQuantidade() * request.getQuantidade();
            double disponivel = item.getIngrediente().getQuantidade();

            if (disponivel < necessario) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ingrediente insuficiente: " + item.getIngrediente().getNome() +
                    ". Necessário: " + necessario +
                    ", Disponível: " + disponivel);
            }
        }

        for (ItemReceita item : receita.getItens()) {
            double consumido = item.getQuantidade() * request.getQuantidade();
            ingredienteService.ajustarEstoque(
                item.getIngrediente().getId(), -consumido);
        }

        produtoService.adicionarAoEstoque(produto.getId(), request.getQuantidade());

        LoteProducao lote = new LoteProducao();
        lote.setProduto(produto);
        lote.setQuantidade(request.getQuantidade());
        lote.setDataProducao(LocalDateTime.now());
        lote.setCustoTotalLote(produto.getCustoProducao() * request.getQuantidade());
        lote.setObservacao(request.getObservacao());

        return loteRepository.save(lote);
    }

    public LoteProducao buscarPorId(Long id) {
        return loteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Lote não encontrado"));
    }
}