package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Despesa;
import com.estoque.estoque_api.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository repository;

    public List<Despesa> listarTodas() {
        return repository.findAll();
    }

    public List<Despesa> listarPorCategoria(Despesa.Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    public Despesa buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Despesa não encontrada"));
    }

    public Despesa registrar(Despesa despesa) {
        despesa.setData(LocalDateTime.now());
        return repository.save(despesa);
    }

    public Despesa atualizar(Long id, Despesa dados) {
        Despesa existente = buscarPorId(id);
        existente.setDescricao(dados.getDescricao());
        existente.setValor(dados.getValor());
        existente.setCategoria(dados.getCategoria());
        existente.setObservacao(dados.getObservacao());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}