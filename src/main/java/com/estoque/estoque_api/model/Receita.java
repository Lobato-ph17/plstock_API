package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receitas")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false, unique = true)
    private Produto produto;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemReceita> itens = new ArrayList<>();

    public Receita() {}

    public double getCustoTotal() {
        return itens.stream()
            .mapToDouble(ItemReceita::getCustoItem)
            .sum();
    }

    public Long getId() { return id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public List<ItemReceita> getItens() { return itens; }
    public void setItens(List<ItemReceita> itens) { this.itens = itens; }
}