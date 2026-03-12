package com.example.estoque_senai.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDate data;

    @Column(length = 255)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    public Movimentacao() {
    }

    public Long getId() {
        return id;
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

    public String getObservacao() {
        return observacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
