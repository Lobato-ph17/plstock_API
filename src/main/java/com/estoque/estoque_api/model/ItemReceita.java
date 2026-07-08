package com.estoque.estoque_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "itens_receita")
public class ItemReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore 
    @ManyToOne
    @JoinColumn(name = "receita_id", nullable = false)
    private Receita receita;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;


    @Positive(message = "Quantidade deve ser maior que zero")
    @Column(nullable = false)
    private double quantidade;

    public ItemReceita() {}

    public double getCustoItem() {
        return quantidade * ingrediente.getCustoPorUnidade();
    }

    public Long getId() { return id; }

    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }

    public Ingrediente getIngrediente() { return ingrediente; }
    public void setIngrediente(Ingrediente ingrediente) { this.ingrediente = ingrediente; }

    public double getQuantidade() { return quantidade; }
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }
}