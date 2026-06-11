package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

   
    boolean existsByNome(String nome);

    @Query("SELECT i FROM Ingrediente i WHERE i.quantidade < i.estoqueMinimo")
    List<Ingrediente> findAbaixoDoMinimo();
}