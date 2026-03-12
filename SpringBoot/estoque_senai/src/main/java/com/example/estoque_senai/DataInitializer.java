package com.example.estoque_senai;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.estoque_senai.Model.AtivoPatrimonial;
import com.example.estoque_senai.Model.Categoria;
import com.example.estoque_senai.Model.Funcionario;
import com.example.estoque_senai.Model.FuncionarioAutenticado;
import com.example.estoque_senai.Model.Produto;
import com.example.estoque_senai.Repository.AtivoPatrimonialRepository;
import com.example.estoque_senai.Repository.CategoriaRepository;
import com.example.estoque_senai.Repository.FuncionarioAutenticadoRepository;
import com.example.estoque_senai.Repository.FuncionarioRepository;
import com.example.estoque_senai.Repository.ProdutoRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private FuncionarioAutenticadoRepository funcionarioAutenticadoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AtivoPatrimonialRepository ativoPatrimonialRepository;

    @Override
    public void run(String... args) throws Exception {
        criarFuncionariosAutorizados();
        criarFuncionarioPadrao();
        criarCategoriasPadrao();
        criarProdutosPadrao();
        criarAtivosPadrao();
    }

    private void criarFuncionariosAutorizados() {
        if (funcionarioAutenticadoRepository.count() > 0) {
            return;
        }

        FuncionarioAutenticado funcionario1 = new FuncionarioAutenticado();
        funcionario1.setNome("Maria Silva");
        funcionario1.setNif("1001");
        funcionario1.setAtivo(true);

        FuncionarioAutenticado funcionario2 = new FuncionarioAutenticado();
        funcionario2.setNome("Carlos Souza");
        funcionario2.setNif("1002");
        funcionario2.setAtivo(true);

        FuncionarioAutenticado funcionario3 = new FuncionarioAutenticado();
        funcionario3.setNome("Ana Lima");
        funcionario3.setNif("1003");
        funcionario3.setAtivo(true);

        funcionarioAutenticadoRepository.save(funcionario1);
        funcionarioAutenticadoRepository.save(funcionario2);
        funcionarioAutenticadoRepository.save(funcionario3);
    }

    private void criarFuncionarioPadrao() {
        if (funcionarioRepository.count() > 0) {
            return;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Maria Silva");
        funcionario.setNif("1001");
        funcionario.setSenha("1234");
        funcionario.setCargo("almoxarife");
        funcionario.setAtivo(true);
        funcionarioRepository.save(funcionario);
    }

    private void criarCategoriasPadrao() {
        if (categoriaRepository.count() > 0) {
            return;
        }

        Categoria categoria1 = new Categoria();
        categoria1.setNome("Informatica");
        categoria1.setDescricao("Materiais usados nos laboratorios de informatica");

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Escritorio");
        categoria2.setDescricao("Materiais de apoio administrativo");

        Categoria categoria3 = new Categoria();
        categoria3.setNome("Patrimonio");
        categoria3.setDescricao("Categorias usadas nos ativos patrimoniais");

        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);
        categoriaRepository.save(categoria3);
    }

    private void criarProdutosPadrao() {
        if (produtoRepository.count() > 0) {
            return;
        }

        Categoria informatica = categoriaRepository.findByNomeIgnoreCase("Informatica").orElse(null);
        Categoria escritorio = categoriaRepository.findByNomeIgnoreCase("Escritorio").orElse(null);

        if (informatica == null || escritorio == null) {
            return;
        }

        Produto produto1 = new Produto();
        produto1.setNome("Mouse USB");
        produto1.setDescricao("Mouse para reposicao dos laboratorios");
        produto1.setPreco(45.0);
        produto1.setQuantidade(20);
        produto1.setUnidadeMedida("un");
        produto1.setCategoria(informatica);

        Produto produto2 = new Produto();
        produto2.setNome("Papel A4");
        produto2.setDescricao("Resmas para secretaria e professores");
        produto2.setPreco(32.5);
        produto2.setQuantidade(50);
        produto2.setUnidadeMedida("resma");
        produto2.setCategoria(escritorio);

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
    }

    private void criarAtivosPadrao() {
        if (ativoPatrimonialRepository.count() > 0) {
            return;
        }

        Categoria patrimonio = categoriaRepository.findByNomeIgnoreCase("Patrimonio").orElse(null);
        if (patrimonio == null) {
            return;
        }

        AtivoPatrimonial ativo1 = new AtivoPatrimonial();
        ativo1.setNome("Notebook Dell");
        ativo1.setDescricao("Notebook usado pela coordenacao");
        ativo1.setNumeroPatrimonio("PAT-2026-001");
        ativo1.setLocalizacao("Coordenacao");
        ativo1.setSituacao("Em uso");
        ativo1.setDataCadastro(LocalDate.now());
        ativo1.setCategoria(patrimonio);

        AtivoPatrimonial ativo2 = new AtivoPatrimonial();
        ativo2.setNome("Projetor Epson");
        ativo2.setDescricao("Projetor da sala de reunioes");
        ativo2.setNumeroPatrimonio("PAT-2026-002");
        ativo2.setLocalizacao("Sala de reunioes");
        ativo2.setSituacao("Em uso");
        ativo2.setDataCadastro(LocalDate.now());
        ativo2.setCategoria(patrimonio);

        ativoPatrimonialRepository.save(ativo1);
        ativoPatrimonialRepository.save(ativo2);
    }
}
