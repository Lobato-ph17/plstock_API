package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Ingrediente;
import com.estoque.estoque_api.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service 
public class IngredienteService {

    @Autowired
    private IngredienteRepository repository;

    public List<Ingrediente> listarTodos() {
        return repository.findAll();
    }

    public List<Ingrediente> listarAbaixoDoMinimo() {
        return repository.findAbaixoDoMinimo();
    }

    public Ingrediente buscarPorId(Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Ingrediente não encontrado"));
    }

    public Ingrediente criar(Ingrediente ingrediente) {
        if (repository.existsByNome(ingrediente.getNome())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Já existe um ingrediente com esse nome");
        }
        return repository.save(ingrediente);
    }

    public Ingrediente atualizar(Long id, Ingrediente dados) {
        Ingrediente existente = buscarPorId(id);

        existente.setNome(dados.getNome());
        existente.setUnidade(dados.getUnidade());
        existente.setCustoPorUnidade(dados.getCustoPorUnidade());
        existente.setEstoqueMinimo(dados.getEstoqueMinimo());

        return repository.save(existente);
    }

    public Ingrediente ajustarEstoque(Long id, double quantidade) {
        Ingrediente ingrediente = buscarPorId(id);
        double novaQuantidade = ingrediente.getQuantidade() + quantidade;

        if (novaQuantidade < 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Estoque não pode ficar negativo");
        }

        ingrediente.setQuantidade(novaQuantidade);
        return repository.save(ingrediente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}