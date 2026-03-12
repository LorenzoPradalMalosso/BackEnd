package com.example.estoque_senai.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String nif;

    @JsonIgnore
    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(nullable = false, length = 80)
    private String cargo = "funcionario";

    public Funcionario() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNif() {
        return nif;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getCargo() {
        return cargo;
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

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
