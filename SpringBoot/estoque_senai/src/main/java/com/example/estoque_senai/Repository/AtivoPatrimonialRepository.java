package com.example.estoque_senai.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estoque_senai.Model.AtivoPatrimonial;

public interface AtivoPatrimonialRepository extends JpaRepository<AtivoPatrimonial, Long> {

    List<AtivoPatrimonial> findAllByOrderByNomeAsc();

    Optional<AtivoPatrimonial> findByNumeroPatrimonio(String numeroPatrimonio);

    boolean existsByCategoriaId(Long categoriaId);
}
