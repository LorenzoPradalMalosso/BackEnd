package com.example.estoque_senai.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    public Categoria() {
    }


    // Getters and Setters
    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
