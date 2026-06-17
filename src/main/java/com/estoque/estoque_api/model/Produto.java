package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;

    @Column
    private String descricao;

    @Positive(message = "Preço de venda deve ser maior que zero")
    @Column(nullable = false)
    private double precoVenda;

    @Min(value = 0, message = "Quantidade não pode ser negativa")
    @Column(nullable = false)
    private int quantidadeEmEstoque;

    @Column(nullable = false)
    private double custoProducao = 0.0;

    public Produto() {}

    @Transient
    public double getMargemLucro() {
        if (custoProducao == 0) return 0;
        return ((precoVenda - custoProducao) / precoVenda) * 100;
    }

    @Transient
    public double getLucroUnitario() {
        return precoVenda - custoProducao;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public int getQuantidadeEmEstoque() { return quantidadeEmEstoque; }
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) { this.quantidadeEmEstoque = quantidadeEmEstoque; }

    public double getCustoProducao() { return custoProducao; }
    public void setCustoProducao(double custoProducao) { this.custoProducao = custoProducao; }
}