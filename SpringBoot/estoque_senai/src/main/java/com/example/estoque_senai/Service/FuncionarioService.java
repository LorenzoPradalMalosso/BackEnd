package com.example.estoque_senai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estoque_senai.Model.Funcionario;
import com.example.estoque_senai.Model.FuncionarioAutenticado;
import com.example.estoque_senai.Repository.FuncionarioAutenticadoRepository;
import com.example.estoque_senai.Repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioAutenticadoRepository funcionarioAutenticadoRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAllByOrderByNomeAsc();
    }

    public Funcionario salvar(Funcionario funcionario) {
        validarFuncionario(funcionario);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario cadastrar(Funcionario funcionario) {
        validarFuncionario(funcionario);
        if (funcionarioRepository.existsByNif(funcionario.getNif())) {
            throw new IllegalArgumentException("Ja existe um funcionario cadastrado com esse NIF");
        }
        FuncionarioAutenticado autorizado = funcionarioAutenticadoRepository.findByNifAndAtivoTrue(funcionario.getNif())
            .orElseThrow(() -> new IllegalArgumentException("NIF nao autorizado para cadastro"));
        if (!autorizado.getNome().equalsIgnoreCase(funcionario.getNome())) {
            throw new IllegalArgumentException("Nome e NIF nao estao autorizados para cadastro");
        }
        if (funcionario.getCargo() == null || funcionario.getCargo().trim().isEmpty()) {
            funcionario.setCargo("funcionario");
        }
        funcionario.setAtivo(true);
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> buscarPorNif(String nif) {
        return funcionarioRepository.findByNif(nif);
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public boolean existePorNif(String nif) {
        return funcionarioRepository.existsByNif(nif);
    }

    public Funcionario autenticar(String nif, String senha) {
        if (nif == null || nif.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Informe NIF e senha");
        }
        Funcionario funcionario = funcionarioRepository.findByNif(nif.trim())
            .orElseThrow(() -> new IllegalArgumentException("NIF ou senha invalidos"));
        if (!funcionario.isAtivo()) {
            throw new IllegalArgumentException("Esse funcionario esta inativo");
        }
        if (!funcionario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("NIF ou senha invalidos");
        }
        return funcionario;
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado, String novaSenha) {
        Funcionario funcionario = funcionarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Funcionario nao encontrado"));

        if (funcionarioAtualizado.getNome() == null || funcionarioAtualizado.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do funcionario");
        }
        if (funcionarioAtualizado.getCargo() == null || funcionarioAtualizado.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o cargo do funcionario");
        }

        funcionario.setNome(funcionarioAtualizado.getNome().trim());
        funcionario.setCargo(funcionarioAtualizado.getCargo().trim());
        funcionario.setAtivo(funcionarioAtualizado.isAtivo());

        if (novaSenha != null && !novaSenha.trim().isEmpty()) {
            if (novaSenha.trim().length() < 4) {
                throw new IllegalArgumentException("A senha precisa ter pelo menos 4 caracteres");
            }
            funcionario.setSenha(novaSenha.trim());
        }

        return funcionarioRepository.save(funcionario);
    }

    public long contar() {
        return funcionarioRepository.count();
    }

    private void validarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionario invalido");
        }
        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do funcionario");
        }
        if (funcionario.getNif() == null || funcionario.getNif().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o NIF do funcionario");
        }
        if (funcionario.getSenha() == null || funcionario.getSenha().trim().length() < 4) {
            throw new IllegalArgumentException("A senha precisa ter pelo menos 4 caracteres");
        }
        funcionario.setNome(funcionario.getNome().trim());
        funcionario.setNif(funcionario.getNif().trim());
        funcionario.setSenha(funcionario.getSenha().trim());
    }
}
