package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Positive(message = "Quantidade deve ser maior que zero")
    @Column(nullable = false)
    private int quantidade;

    @Positive(message = "Preço deve ser maior que zero")
    @Column(nullable = false)
    private double precoUnitario;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    @Column(nullable = false)
    private double lucroTotal;

    @Column(nullable = false)
    private double receitaBruta;

    @Column
    private String observacao;

    public Venda() {}

    public Long getId() { return id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }

    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) { this.dataVenda = dataVenda; }

    public double getLucroTotal() { return lucroTotal; }
    public void setLucroTotal(double lucroTotal) { this.lucroTotal = lucroTotal; }

    public double getReceitaBruta() { return receitaBruta; }
    public void setReceitaBruta(double receitaBruta) { this.receitaBruta = receitaBruta; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}