package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.LoteProducaoRequest;
import com.estoque.estoque_api.model.LoteProducao;
import com.estoque.estoque_api.service.LoteProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lotes")
@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}")
public class LoteProducaoController {

    @Autowired
    private LoteProducaoService service;

    @GetMapping
    public List<LoteProducao> listar() {
        return service.listarTodos();
    }

    @GetMapping("/produto/{produtoId}")
    public List<LoteProducao> listarPorProduto(@PathVariable Long produtoId) {
        return service.listarPorProduto(produtoId);
    }

    @GetMapping("/{id}")
    public LoteProducao buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public LoteProducao registrar(@RequestBody LoteProducaoRequest request) {
        return service.registrar(request);
    }
}