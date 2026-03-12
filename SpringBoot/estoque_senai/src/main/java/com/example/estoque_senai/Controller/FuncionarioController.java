package com.example.estoque_senai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.estoque_senai.Model.Funcionario;
import com.example.estoque_senai.Service.FuncionarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/funcionarios")
    public String listarFuncionarios(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "funcionarios/lista";
    }

    @GetMapping("/funcionarios/editar/{id}")
    public String editarFuncionario(HttpSession session, @PathVariable Long id, Model model,
        RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        if (funcionario == null) {
            attributes.addFlashAttribute("msg", "Funcionario nao encontrado");
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/funcionarios";
        }
        model.addAttribute("funcionario", funcionario);
        return "funcionarios/editar";
    }

    @PostMapping("/funcionarios/atualizar/{id}")
    public String atualizarFuncionario(HttpSession session, @PathVariable Long id, Funcionario funcionario,
        @RequestParam(required = false) String novaSenha, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            funcionarioService.atualizar(id, funcionario, novaSenha);
            attributes.addFlashAttribute("msg", "Funcionario atualizado com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/funcionarios";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/funcionarios/editar/" + id;
        }
    }

    private boolean usuarioNaoLogado(HttpSession session) {
        return session.getAttribute("funcionarioLogadoId") == null;
    }
}
