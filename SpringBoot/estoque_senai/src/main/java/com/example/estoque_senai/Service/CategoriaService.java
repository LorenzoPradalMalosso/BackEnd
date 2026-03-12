package com.example.estoque_senai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estoque_senai.Model.Categoria;
import com.example.estoque_senai.Repository.AtivoPatrimonialRepository;
import com.example.estoque_senai.Repository.CategoriaRepository;
import com.example.estoque_senai.Repository.ProdutoRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AtivoPatrimonialRepository ativoPatrimonialRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAllByOrderByNomeAsc();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria buscarEntidadePorId(Long id) {
        Categoria categoria = buscarPorId(id);
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria nao encontrada");
        }
        return categoria;
    }

    public Categoria salvar(Categoria categoria) {
        validarCategoria(categoria);
        validarNomeDuplicado(categoria);
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id) {
        Categoria categoria = buscarEntidadePorId(id);
        if (produtoRepository.existsByCategoriaId(categoria.getId())) {
            throw new IllegalArgumentException("Nao e possivel excluir uma categoria com materiais vinculados");
        }
        if (ativoPatrimonialRepository.existsByCategoriaId(categoria.getId())) {
            throw new IllegalArgumentException("Nao e possivel excluir uma categoria com ativos vinculados");
        }
        categoriaRepository.delete(categoria);
    }

    public long contar() {
        return categoriaRepository.count();
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria invalida");
        }
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome da categoria");
        }
        categoria.setNome(categoria.getNome().trim());
        if (categoria.getDescricao() != null) {
            categoria.setDescricao(categoria.getDescricao().trim());
        }
    }

    private void validarNomeDuplicado(Categoria categoria) {
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findByNomeIgnoreCase(categoria.getNome());
        if (categoriaEncontrada.isPresent() && !categoriaEncontrada.get().getId().equals(categoria.getId())) {
            throw new IllegalArgumentException("Ja existe uma categoria com esse nome");
        }
    }
}
