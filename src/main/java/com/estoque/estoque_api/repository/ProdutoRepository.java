package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByNome(String nome);

    @Query("SELECT p FROM Produto p WHERE p.quantidadeEmEstoque = 0")
    List<Produto> findSemEstoque();

    @Query("SELECT p FROM Produto p WHERE p.quantidadeEmEstoque <= :minimo AND p.quantidadeEmEstoque > 0")
    List<Produto> findComEstoqueBaixo(int minimo);
}