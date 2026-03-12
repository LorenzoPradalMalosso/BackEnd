package com.example.estoque_senai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estoque_senai.Model.FuncionarioAutenticado;

public interface FuncionarioAutenticadoRepository extends JpaRepository<FuncionarioAutenticado, Long> {

    Optional<FuncionarioAutenticado> findByNifAndAtivoTrue(String nif);

    boolean existsByNifAndAtivoTrue(String nif);
}
