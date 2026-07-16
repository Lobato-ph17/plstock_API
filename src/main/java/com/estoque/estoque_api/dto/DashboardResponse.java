package com.estoque.estoque_api.dto;

import java.util.List;
import java.util.Map;

public class DashboardResponse {

    private double faturamentoBruto;
    private double totalCustoProducao;
    private double totalDespesas;
    private double lucroLiquido;
    private long totalVendas;
    private long totalLotes;
    private int ingredientesAbaixoDoMinimo;
    private int produtosSemEstoque;
    private List<Map<String, Object>> rankingProdutos;

    public DashboardResponse() {}

    public double getFaturamentoBruto() { return faturamentoBruto; }
    public void setFaturamentoBruto(double faturamentoBruto) { this.faturamentoBruto = faturamentoBruto; }

    public double getTotalCustoProducao() { return totalCustoProducao; }
    public void setTotalCustoProducao(double totalCustoProducao) { this.totalCustoProducao = totalCustoProducao; }

    public double getTotalDespesas() { return totalDespesas; }
    public void setTotalDespesas(double totalDespesas) { this.totalDespesas = totalDespesas; }

    public double getLucroLiquido() { return lucroLiquido; }
    public void setLucroLiquido(double lucroLiquido) { this.lucroLiquido = lucroLiquido; }

    public long getTotalVendas() { return totalVendas; }
    public void setTotalVendas(long totalVendas) { this.totalVendas = totalVendas; }

    public long getTotalLotes() { return totalLotes; }
    public void setTotalLotes(long totalLotes) { this.totalLotes = totalLotes; }

    public int getIngredientesAbaixoDoMinimo() { return ingredientesAbaixoDoMinimo; }
    public void setIngredientesAbaixoDoMinimo(int ingredientesAbaixoDoMinimo) { this.ingredientesAbaixoDoMinimo = ingredientesAbaixoDoMinimo; }

    public int getProdutosSemEstoque() { return produtosSemEstoque; }
    public void setProdutosSemEstoque(int produtosSemEstoque) { this.produtosSemEstoque = produtosSemEstoque; }

    public List<Map<String, Object>> getRankingProdutos() { return rankingProdutos; }
    public void setRankingProdutos(List<Map<String, Object>> rankingProdutos) { this.rankingProdutos = rankingProdutos; }
}