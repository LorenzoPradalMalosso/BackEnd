package com.example.estoque_senai.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estoque_senai.Model.Categoria;
import com.example.estoque_senai.Model.Produto;
import com.example.estoque_senai.Repository.MovimentacaoRepository;
import com.example.estoque_senai.Repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAllByOrderByNomeAsc();
    }

    public Produto salvar(Produto produto, Long categoriaId) {
        validarProduto(produto);
        Categoria categoria = categoriaService.buscarEntidadePorId(categoriaId);
        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto buscarEntidadePorId(Long id) {
        Produto produto = buscarPorId(id);
        if (produto == null) {
            throw new IllegalArgumentException("Material nao encontrado");
        }
        return produto;
    }

    public void deletar(Long id) {
        Produto produto = buscarEntidadePorId(id);
        if (movimentacaoRepository.existsByProdutoId(id)) {
            throw new IllegalArgumentException("Nao e possivel excluir um material com movimentacoes registradas");
        }
        produtoRepository.delete(produto);
    }

    public long contar() {
        return produtoRepository.count();
    }

    private void validarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Material invalido");
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do material");
        }
        if (produto.getPreco() == null || produto.getPreco() < 0) {
            throw new IllegalArgumentException("Informe um preco valido");
        }
        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Informe uma quantidade valida");
        }
        if (produto.getUnidadeMedida() == null || produto.getUnidadeMedida().trim().isEmpty()) {
            produto.setUnidadeMedida("un");
        }
        produto.setNome(produto.getNome().trim());
        produto.setUnidadeMedida(produto.getUnidadeMedida().trim());
        if (produto.getDescricao() != null) {
            produto.setDescricao(produto.getDescricao().trim());
        }
    }
}
