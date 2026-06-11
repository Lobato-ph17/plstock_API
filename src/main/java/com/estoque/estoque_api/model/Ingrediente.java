package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ingredientes")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeMedida unidade;

    @Min(value = 0, message = "Quantidade não pode ser negativa")
    @Column(nullable = false)
    private double quantidade;

    @Positive(message = "Custo deve ser maior que zero")
    @Column(nullable = false)
    private double custoPorUnidade;

    @Min(value = 0, message = "Estoque mínimo não pode ser negativo")
    @Column(nullable = false)
    private double estoqueMinimo;

    public enum UnidadeMedida {
        GRAMA, MILILITRO, UNIDADE
    }

    public Ingrediente() {}

    // Getters e Setters
    
    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public UnidadeMedida getUnidade() { return unidade; }
    public void setUnidade(UnidadeMedida unidade) { this.unidade = unidade; }

    public double getQuantidade() { return quantidade; }
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

    public double getCustoPorUnidade() { return custoPorUnidade; }
    public void setCustoPorUnidade(double custoPorUnidade) { this.custoPorUnidade = custoPorUnidade; }

    public double getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(double estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
}