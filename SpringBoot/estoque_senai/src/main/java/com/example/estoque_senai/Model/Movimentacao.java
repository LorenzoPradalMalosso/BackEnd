package com.example.estoque_senai.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimentacao;

    private String tipo;
    private Integer quantidade;
    private LocalDate data;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Movimentacao() {
    }

    // Construtor com parâmetros
    public Movimentacao(Long idMovimentacao, String tipo, Integer quantidade, LocalDate data) {
        this.idMovimentacao = idMovimentacao;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }


    // Getters and Setters
    public Long getIdMovimentacao() {
        return idMovimentacao;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public Produto getProduto() {
        return produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setIdMovimentacao(Long idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
