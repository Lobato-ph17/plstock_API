package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/sem-estoque")
    public List<Produto> semEstoque() {
        return service.listarSemEstoque();
    }

    @GetMapping("/estoque-baixo")
    public List<Produto> estoqueBaixo(@RequestParam(defaultValue = "5") int minimo) {
        return service.listarEstoqueBaixo(minimo);
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Produto criar(@Valid @RequestBody Produto produto) {
        return service.criar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @Valid @RequestBody Produto dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}