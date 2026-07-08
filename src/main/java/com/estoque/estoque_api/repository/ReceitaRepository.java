package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Optional<Receita> findByProdutoId(Long produtoId);
    boolean existsByProdutoId(Long produtoId);
}