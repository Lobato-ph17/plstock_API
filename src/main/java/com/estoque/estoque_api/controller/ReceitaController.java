package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.ReceitaRequest;
import com.estoque.estoque_api.model.Receita;
import com.estoque.estoque_api.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/receitas")
@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @GetMapping
    public List<Receita> listar() {
        return service.listarTodas();
    }

    @GetMapping("/produto/{produtoId}")
    public Receita buscarPorProduto(@PathVariable Long produtoId) {
        return service.buscarPorProduto(produtoId);
    }

    @PostMapping
    public Receita salvar(@RequestBody ReceitaRequest request) {
        return service.salvar(request);
    }

    @DeleteMapping("/produto/{produtoId}")
    public void deletar(@PathVariable Long produtoId) {
        service.deletar(produtoId);
    }
}