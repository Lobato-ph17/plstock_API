package com.estoque.estoque_api.dto;

import java.util.List;

public class ReceitaRequest {

    private Long produtoId;
    private List<ItemRequest> itens;

    public static class ItemRequest {
        private Long ingredienteId;
        private double quantidade;

        public Long getIngredienteId() { return ingredienteId; }
        public void setIngredienteId(Long ingredienteId) { this.ingredienteId = ingredienteId; }

        public double getQuantidade() { return quantidade; }
        public void setQuantidade(double quantidade) { this.quantidade = quantidade; }
    }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public List<ItemRequest> getItens() { return itens; }
    public void setItens(List<ItemRequest> itens) { this.itens = itens; }
}