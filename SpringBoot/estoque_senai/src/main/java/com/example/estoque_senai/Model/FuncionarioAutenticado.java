package com.example.estoque_senai.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios_autenticados")
public class FuncionarioAutenticado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String nif;

    @Column(nullable = false)
    private boolean ativo = true;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNif() {
        return nif;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
