package com.example.estoque_senai.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.estoque_senai.Model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByNif(String nif);

    boolean existsByNif(String nif);

    List<Funcionario> findAllByOrderByNomeAsc();
}
