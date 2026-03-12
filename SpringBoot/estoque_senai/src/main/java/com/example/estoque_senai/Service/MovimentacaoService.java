package com.example.estoque_senai.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.estoque_senai.Model.Funcionario;
import com.example.estoque_senai.Model.Movimentacao;
import com.example.estoque_senai.Model.Produto;
import com.example.estoque_senai.Repository.FuncionarioRepository;
import com.example.estoque_senai.Repository.MovimentacaoRepository;
import com.example.estoque_senai.Repository.ProdutoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Movimentacao> listarTodos() {
        return movimentacaoRepository.findAllByOrderByDataDescIdDesc();
    }

    @Transactional
    public Movimentacao registrarEntrada(Movimentacao movimentacao, Long produtoId, Long funcionarioId) {
        return salvarMovimentacao(movimentacao, produtoId, funcionarioId, "ENTRADA");
    }

    @Transactional
    public Movimentacao registrarSaida(Movimentacao movimentacao, Long produtoId, Long funcionarioId) {
        return salvarMovimentacao(movimentacao, produtoId, funcionarioId, "SAIDA");
    }

    public Movimentacao buscarPorId(Long id) {
        return movimentacaoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Movimentacao atualizar(Long id, Movimentacao movimentacaoAtualizada, Long produtoId, Long funcionarioId) {
        Movimentacao movimentacao = buscarPorId(id);
        if (movimentacao == null) {
            throw new IllegalArgumentException("Movimentacao nao encontrada");
        }

        Produto produtoAntigo = movimentacao.getProduto();
        desfazerSaldo(produtoAntigo, movimentacao.getTipo(), movimentacao.getQuantidade());
        produtoRepository.save(produtoAntigo);

        Produto novoProduto = buscarProduto(produtoId);
        Funcionario funcionario = buscarFuncionario(funcionarioId);
        prepararMovimentacao(movimentacaoAtualizada);
        aplicarSaldo(novoProduto, movimentacaoAtualizada.getTipo(), movimentacaoAtualizada.getQuantidade());
        produtoRepository.save(novoProduto);

        movimentacao.setTipo(movimentacaoAtualizada.getTipo().toUpperCase());
        movimentacao.setQuantidade(movimentacaoAtualizada.getQuantidade());
        movimentacao.setData(movimentacaoAtualizada.getData());
        movimentacao.setObservacao(movimentacaoAtualizada.getObservacao());
        movimentacao.setProduto(novoProduto);
        movimentacao.setFuncionario(funcionario);
        return movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void deletar(Long id) {
        Movimentacao movimentacao = buscarPorId(id);
        if (movimentacao == null) {
            throw new IllegalArgumentException("Movimentacao nao encontrada");
        }
        Produto produto = movimentacao.getProduto();
        desfazerSaldo(produto, movimentacao.getTipo(), movimentacao.getQuantidade());
        produtoRepository.save(produto);
        movimentacaoRepository.delete(movimentacao);
    }

    public long contar() {
        return movimentacaoRepository.count();
    }

    private Movimentacao salvarMovimentacao(Movimentacao movimentacao, Long produtoId, Long funcionarioId, String tipo) {
        Produto produto = buscarProduto(produtoId);
        Funcionario funcionario = buscarFuncionario(funcionarioId);
        movimentacao.setTipo(tipo);
        prepararMovimentacao(movimentacao);
        aplicarSaldo(produto, movimentacao.getTipo(), movimentacao.getQuantidade());
        produtoRepository.save(produto);
        movimentacao.setProduto(produto);
        movimentacao.setFuncionario(funcionario);
        return movimentacaoRepository.save(movimentacao);
    }

    private void prepararMovimentacao(Movimentacao movimentacao) {
        if (movimentacao == null) {
            throw new IllegalArgumentException("Movimentacao invalida");
        }
        if (movimentacao.getQuantidade() == null || movimentacao.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Informe uma quantidade maior que zero");
        }
        if (movimentacao.getData() == null) {
            movimentacao.setData(LocalDate.now());
        }
        if (movimentacao.getTipo() == null || movimentacao.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o tipo da movimentacao");
        }
        movimentacao.setTipo(movimentacao.getTipo().trim().toUpperCase());
        if (movimentacao.getObservacao() != null) {
            movimentacao.setObservacao(movimentacao.getObservacao().trim());
        }
    }

    private Produto buscarProduto(Long produtoId) {
        if (produtoId == null) {
            throw new IllegalArgumentException("Informe o material da movimentacao");
        }
        return produtoRepository.findById(produtoId)
            .orElseThrow(() -> new IllegalArgumentException("Material nao encontrado"));
    }

    private Funcionario buscarFuncionario(Long funcionarioId) {
        if (funcionarioId == null) {
            throw new IllegalArgumentException("Funcionario nao informado");
        }
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
            .orElseThrow(() -> new IllegalArgumentException("Funcionario nao encontrado"));
        if (!funcionario.isAtivo()) {
            throw new IllegalArgumentException("O funcionario precisa estar ativo para registrar movimentacoes");
        }
        return funcionario;
    }

    private void aplicarSaldo(Produto produto, String tipo, Integer quantidade) {
        if ("ENTRADA".equalsIgnoreCase(tipo)) {
            produto.setQuantidade(produto.getQuantidade() + quantidade);
            return;
        }
        if ("SAIDA".equalsIgnoreCase(tipo)) {
            if (produto.getQuantidade() < quantidade) {
                throw new IllegalArgumentException("Estoque insuficiente para registrar a saida");
            }
            produto.setQuantidade(produto.getQuantidade() - quantidade);
            return;
        }
        throw new IllegalArgumentException("Tipo de movimentacao invalido");
    }

    private void desfazerSaldo(Produto produto, String tipo, Integer quantidade) {
        if ("ENTRADA".equalsIgnoreCase(tipo)) {
            if (produto.getQuantidade() < quantidade) {
                throw new IllegalArgumentException("Nao e possivel remover essa movimentacao porque o estoque atual esta menor");
            }
            produto.setQuantidade(produto.getQuantidade() - quantidade);
            return;
        }
        if ("SAIDA".equalsIgnoreCase(tipo)) {
            produto.setQuantidade(produto.getQuantidade() + quantidade);
            return;
        }
        throw new IllegalArgumentException("Tipo de movimentacao invalido");
    }
}
