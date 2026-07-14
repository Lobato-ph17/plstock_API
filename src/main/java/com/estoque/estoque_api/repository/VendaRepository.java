package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByProdutoIdOrderByDataVendaDesc(Long produtoId);

    @Query("SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :inicio AND :fim ORDER BY v.dataVenda DESC")
    List<Venda> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT COALESCE(SUM(v.receitaBruta), 0) FROM Venda v WHERE v.dataVenda BETWEEN :inicio AND :fim")
    double sumReceitaByPeriodo(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT COALESCE(SUM(v.lucroTotal), 0) FROM Venda v WHERE v.dataVenda BETWEEN :inicio AND :fim")
    double sumLucroByPeriodo(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT v.produto.id, SUM(v.quantidade) FROM Venda v GROUP BY v.produto.id ORDER BY SUM(v.quantidade) DESC")
    List<Object[]> findRankingProdutos();
}