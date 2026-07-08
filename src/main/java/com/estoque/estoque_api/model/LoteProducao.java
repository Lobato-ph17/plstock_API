package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "lotes_producao")
public class LoteProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Positive(message = "Quantidade deve ser maior que zero")
    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private LocalDateTime dataProducao;

    @Column(nullable = false)
    private double custoTotalLote;
    
    @Column
    private String observacao;

    public LoteProducao() {}

    public Long getId() { return id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataProducao() { return dataProducao; }
    public void setDataProducao(LocalDateTime dataProducao) { this.dataProducao = dataProducao; }

    public double getCustoTotalLote() { return custoTotalLote; }
    public void setCustoTotalLote(double custoTotalLote) { this.custoTotalLote = custoTotalLote; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}