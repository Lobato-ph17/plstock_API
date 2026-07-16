package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.model.Despesa;
import com.estoque.estoque_api.service.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/despesas")
@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}")
public class DespesaController {

    @Autowired
    private DespesaService service;

    @GetMapping
    public List<Despesa> listar() {
        return service.listarTodas();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Despesa> listarPorCategoria(@PathVariable Despesa.Categoria categoria) {
        return service.listarPorCategoria(categoria);
    }

    @GetMapping("/{id}")
    public Despesa buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Despesa registrar(@Valid @RequestBody Despesa despesa) {
        return service.registrar(despesa);
    }

    @PutMapping("/{id}")
    public Despesa atualizar(@PathVariable Long id, @Valid @RequestBody Despesa dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}