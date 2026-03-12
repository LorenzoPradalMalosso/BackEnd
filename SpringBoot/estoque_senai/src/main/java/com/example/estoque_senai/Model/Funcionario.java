package com.example.estoque_senai.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    private String nif;
    private String nome;
    private String senha;
    private String cargo;

    @OneToMany(mappedBy = "funcionario")
    private List<Movimentacao> movimentacoes;

    public Funcionario() {
    }

    // Construtor com parâmetros
    public Funcionario(Long idFuncionario, String nif, String nome, String senha, String cargo) {
        this.idFuncionario = idFuncionario;
        this.nif = nif;
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
    }


    // Getters and Setters
    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public String getNif() {
        return nif;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getCargo() {
        return cargo;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
    
}
