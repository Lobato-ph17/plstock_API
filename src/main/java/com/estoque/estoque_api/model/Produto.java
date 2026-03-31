package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "produtos")

public class Produto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Nome é obrigatório")
        @Column(nullable = false)
        private String nome;

        @Min(value = 0, message = "Quantidade não pode ser negativa")
        @Column(nullable = false)
        private int quantidade;



        @Positive(message = "Preço deve ser maior que zero")
        @Column(nullable = false)
        private double preco;

        public Produto() {
    
        }

        public Produto(String nome, int quantidade, double preco) {
            this.nome= nome;
            this.quantidade = quantidade;
            this.preco = preco;
        }

        public String getNome() {
         return nome;
        }

        public void setNome(String novoNome){
            this.nome = novoNome;
        }


        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int novaQuantidade){
            this.quantidade = novaQuantidade;
        }


        public double getPreco() {
            return preco;
        }

        public void setPreco(double novoPreco){
            this.preco = novoPreco;
        }

       public Long getId(){
        return id;
       }
}
   



    
