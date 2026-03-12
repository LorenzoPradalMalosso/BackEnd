package com.example.estoque_senai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estoque_senai.Model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByOrderByNomeAsc();

    boolean existsByCategoriaId(Long categoriaId);
}
