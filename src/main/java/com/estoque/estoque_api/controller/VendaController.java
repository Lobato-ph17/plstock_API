package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.VendaRequest;
import com.estoque.estoque_api.model.Venda;
import com.estoque.estoque_api.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}")
public class VendaController {

    @Autowired
    private VendaService service;

    @GetMapping
    public List<Venda> listar() {
        return service.listarTodas();
    }

    @GetMapping("/produto/{produtoId}")
    public List<Venda> listarPorProduto(@PathVariable Long produtoId) {
        return service.listarPorProduto(produtoId);
    }

    @GetMapping("/{id}")
    public Venda buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Venda registrar(@RequestBody VendaRequest request) {
        return service.registrar(request);
    }
}