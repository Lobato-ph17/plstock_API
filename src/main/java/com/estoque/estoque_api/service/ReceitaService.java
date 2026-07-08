package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.ReceitaRequest;
import com.estoque.estoque_api.model.*;
import com.estoque.estoque_api.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private IngredienteService ingredienteService;

    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }

    public Receita buscarPorProduto(Long produtoId) {
        return receitaRepository.findByProdutoId(produtoId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Receita não encontrada para esse produto"));
    }

    public Receita salvar(ReceitaRequest request) {
        Produto produto = produtoService.buscarPorId(request.getProdutoId());

        Receita receita = receitaRepository.findByProdutoId(produto.getId())
            .orElse(new Receita());

        receita.setProduto(produto);

        receita.getItens().clear();

        for (ReceitaRequest.ItemRequest itemReq : request.getItens()) {
            Ingrediente ingrediente = ingredienteService.buscarPorId(itemReq.getIngredienteId());

            ItemReceita item = new ItemReceita();
            item.setReceita(receita);
            item.setIngrediente(ingrediente);
            item.setQuantidade(itemReq.getQuantidade());

            receita.getItens().add(item);
        }

        Receita salva = receitaRepository.save(receita);

        produtoService.atualizarCustoProducao(produto.getId(), salva.getCustoTotal());

        return salva;
    }

    public void deletar(Long produtoId) {
        Receita receita = buscarPorProduto(produtoId);
        receitaRepository.delete(receita);
        produtoService.atualizarCustoProducao(produtoId, 0.0);
    }
}