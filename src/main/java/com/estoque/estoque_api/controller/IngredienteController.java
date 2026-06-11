package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.model.Ingrediente;
import com.estoque.estoque_api.service.IngredienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ingredientes")
@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}")
public class IngredienteController {

    @Autowired
    private IngredienteService service;

    @GetMapping
    public List<Ingrediente> listar() {
        return service.listarTodos();
    }

    @GetMapping("/alertas")
    public List<Ingrediente> alertas() {
        return service.listarAbaixoDoMinimo();
    }

    @GetMapping("/{id}")
    public Ingrediente buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Ingrediente criar(@Valid @RequestBody Ingrediente ingrediente) {
        return service.criar(ingrediente);
    }

    @PutMapping("/{id}")
    public Ingrediente atualizar(@PathVariable Long id, @Valid @RequestBody Ingrediente dados) {
        return service.atualizar(id, dados);
    }

    @PatchMapping("/{id}/estoque")
    public Ingrediente ajustarEstoque(@PathVariable Long id, @RequestBody Map<String, Double> body) {
        return service.ajustarEstoque(id, body.get("quantidade"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}