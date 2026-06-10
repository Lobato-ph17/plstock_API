package com.estoque.estoque_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estoque.estoque_api.model.Produto;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import jakarta.validation.Valid;
import java.util.Map;



import com.estoque.estoque_api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")

@CrossOrigin(origins = "https://plstock.netlify.app")


public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List <Produto> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Produto adicionar(@Valid @RequestBody Produto produto) {
    return repository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
    repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
    return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Map<String, Object> dados) {
    return repository.findById(id)
        .map(produto -> {
            if (dados.containsKey("nome"))
                produto.setNome((String) dados.get("nome"));
            if (dados.containsKey("preco"))
                produto.setPreco(((Number) dados.get("preco")).doubleValue());
            if (dados.containsKey("quantidade"))
                produto.setQuantidade(produto.getQuantidade() + ((Number) dados.get("quantidade")).intValue());
            return repository.save(produto);
        })
        .orElse(null);
}
    
}

