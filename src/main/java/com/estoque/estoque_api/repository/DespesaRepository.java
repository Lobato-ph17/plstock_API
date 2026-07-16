package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findByCategoria(Despesa.Categoria categoria);

    @Query("SELECT d FROM Despesa d WHERE d.data BETWEEN :inicio AND :fim ORDER BY d.data DESC")
    List<Despesa> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT COALESCE(SUM(d.valor), 0) FROM Despesa d WHERE d.data BETWEEN :inicio AND :fim")
    double sumByPeriodo(LocalDateTime inicio, LocalDateTime fim);
}