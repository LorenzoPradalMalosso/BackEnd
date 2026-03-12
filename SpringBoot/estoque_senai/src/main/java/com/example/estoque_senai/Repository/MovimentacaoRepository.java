package com.example.estoque_senai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.estoque_senai.Model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findAllByOrderByDataDescIdDesc();

    boolean existsByProdutoId(Long produtoId);
}
