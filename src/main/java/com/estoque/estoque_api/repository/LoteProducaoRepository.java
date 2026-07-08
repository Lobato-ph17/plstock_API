package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.LoteProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface LoteProducaoRepository extends JpaRepository<LoteProducao, Long> {

    List<LoteProducao> findByProdutoIdOrderByDataProducaoDesc(Long produtoId);

    @Query("SELECT l FROM LoteProducao l WHERE l.dataProducao BETWEEN :inicio AND :fim ORDER BY l.dataProducao DESC")
    List<LoteProducao> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT COALESCE(SUM(l.custoTotalLote), 0) FROM LoteProducao l WHERE l.dataProducao BETWEEN :inicio AND :fim")
    double sumCustoByPeriodo(LocalDateTime inicio, LocalDateTime fim);
}