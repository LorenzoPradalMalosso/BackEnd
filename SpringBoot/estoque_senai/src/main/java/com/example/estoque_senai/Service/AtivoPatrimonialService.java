package com.example.estoque_senai.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estoque_senai.Model.AtivoPatrimonial;
import com.example.estoque_senai.Model.Categoria;
import com.example.estoque_senai.Repository.AtivoPatrimonialRepository;

@Service
public class AtivoPatrimonialService {

    @Autowired
    private AtivoPatrimonialRepository ativoPatrimonialRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<AtivoPatrimonial> listarTodos() {
        return ativoPatrimonialRepository.findAllByOrderByNomeAsc();
    }

    public AtivoPatrimonial buscarPorId(Long id) {
        return ativoPatrimonialRepository.findById(id).orElse(null);
    }

    public AtivoPatrimonial salvar(AtivoPatrimonial ativoPatrimonial, Long categoriaId) {
        validarAtivo(ativoPatrimonial);
        validarNumeroPatrimonioDuplicado(ativoPatrimonial);
        Categoria categoria = categoriaService.buscarEntidadePorId(categoriaId);
        ativoPatrimonial.setCategoria(categoria);
        if (ativoPatrimonial.getDataCadastro() == null) {
            ativoPatrimonial.setDataCadastro(LocalDate.now());
        }
        return ativoPatrimonialRepository.save(ativoPatrimonial);
    }

    public void deletar(Long id) {
        AtivoPatrimonial ativoPatrimonial = buscarPorId(id);
        if (ativoPatrimonial == null) {
            throw new IllegalArgumentException("Ativo patrimonial nao encontrado");
        }
        ativoPatrimonialRepository.delete(ativoPatrimonial);
    }

    public long contar() {
        return ativoPatrimonialRepository.count();
    }

    private void validarAtivo(AtivoPatrimonial ativoPatrimonial) {
        if (ativoPatrimonial == null) {
            throw new IllegalArgumentException("Ativo patrimonial invalido");
        }
        if (ativoPatrimonial.getNome() == null || ativoPatrimonial.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do ativo patrimonial");
        }
        if (ativoPatrimonial.getNumeroPatrimonio() == null || ativoPatrimonial.getNumeroPatrimonio().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o numero do patrimonio");
        }
        if (ativoPatrimonial.getLocalizacao() == null || ativoPatrimonial.getLocalizacao().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe a localizacao do ativo");
        }
        if (ativoPatrimonial.getSituacao() == null || ativoPatrimonial.getSituacao().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe a situacao do ativo");
        }
        ativoPatrimonial.setNome(ativoPatrimonial.getNome().trim());
        ativoPatrimonial.setNumeroPatrimonio(ativoPatrimonial.getNumeroPatrimonio().trim());
        ativoPatrimonial.setLocalizacao(ativoPatrimonial.getLocalizacao().trim());
        ativoPatrimonial.setSituacao(ativoPatrimonial.getSituacao().trim());
        if (ativoPatrimonial.getDescricao() != null) {
            ativoPatrimonial.setDescricao(ativoPatrimonial.getDescricao().trim());
        }
    }

    private void validarNumeroPatrimonioDuplicado(AtivoPatrimonial ativoPatrimonial) {
        Optional<AtivoPatrimonial> ativoEncontrado = ativoPatrimonialRepository
            .findByNumeroPatrimonio(ativoPatrimonial.getNumeroPatrimonio());

        if (ativoEncontrado.isPresent() && !ativoEncontrado.get().getId().equals(ativoPatrimonial.getId())) {
            throw new IllegalArgumentException("Ja existe um ativo com esse numero de patrimonio");
        }
    }
}
