package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.DashboardResponse;
import com.estoque.estoque_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private LoteProducaoRepository loteRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public DashboardResponse getDashboard(LocalDateTime inicio, LocalDateTime fim) {
        DashboardResponse response = new DashboardResponse();

        // Faturamento e lucro de vendas no período
        double faturamento = vendaRepository.sumReceitaByPeriodo(inicio, fim);
        double lucroVendas = vendaRepository.sumLucroByPeriodo(inicio, fim);

        // Custo total de produção no período
        double custoProducao = loteRepository.sumCustoByPeriodo(inicio, fim);

        // Total de despesas no período
        double despesas = despesaRepository.sumByPeriodo(inicio, fim);

        // Lucro líquido = lucro das vendas - despesas gerais
        double lucroLiquido = lucroVendas - despesas;

        response.setFaturamentoBruto(faturamento);
        response.setTotalCustoProducao(custoProducao);
        response.setTotalDespesas(despesas);
        response.setLucroLiquido(lucroLiquido);
        response.setTotalVendas(vendaRepository.count());
        response.setTotalLotes(loteRepository.count());

        // Alertas de estoque
        response.setIngredientesAbaixoDoMinimo(
            ingredienteRepository.findAbaixoDoMinimo().size());
        response.setProdutosSemEstoque(
            produtoRepository.findSemEstoque().size());

        // Ranking de produtos mais vendidos
        List<Object[]> ranking = vendaRepository.findRankingProdutos();
        List<Map<String, Object>> rankingFormatado = new ArrayList<>();

        for (Object[] linha : ranking) {
            Map<String, Object> item = new HashMap<>();
            Long produtoId = (Long) linha[0];
            Long totalVendido = (Long) linha[1];

            produtoRepository.findById(produtoId).ifPresent(produto -> {
                item.put("produtoId", produtoId);
                item.put("nome", produto.getNome());
                item.put("totalVendido", totalVendido);
                item.put("faturamento", totalVendido * produto.getPrecoVenda());
            });

            rankingFormatado.add(item);
        }

        response.setRankingProdutos(rankingFormatado);

        return response;
    }
}