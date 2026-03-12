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
@Table(name = "ativos_patrimoniais")
public class AtivoPatrimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false, unique = true, length = 50)
    private String numeroPatrimonio;

    @Column(nullable = false, length = 120)
    private String localizacao;

    @Column(nullable = false, length = 60)
    private String situacao;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public AtivoPatrimonial() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
