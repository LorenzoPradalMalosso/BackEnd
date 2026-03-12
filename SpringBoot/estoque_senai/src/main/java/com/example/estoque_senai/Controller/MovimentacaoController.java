package com.example.estoque_senai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.estoque_senai.Model.Movimentacao;
import com.example.estoque_senai.Service.MovimentacaoService;
import com.example.estoque_senai.Service.ProdutoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/movimentacoes")
    public String listarMovimentacoes(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("movimentacoes", movimentacaoService.listarTodos());
        return "movimentacoes/lista";
    }

    @GetMapping("/movimentacoes/entrada")
    public String entrada(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("movimentacao", new Movimentacao());
        model.addAttribute("produtos", produtoService.listarTodos());
        return "movimentacoes/entrada";
    }

    @PostMapping("/movimentacoes/entrada")
    public String salvarEntrada(HttpSession session, Movimentacao movimentacao, Long produtoId,
        RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            movimentacaoService.registrarEntrada(movimentacao, produtoId, funcionarioLogadoId(session));
            attributes.addFlashAttribute("msg", "Entrada registrada com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/movimentacoes";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/movimentacoes/entrada";
        }
    }

    @GetMapping("/movimentacoes/saida")
    public String saida(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("movimentacao", new Movimentacao());
        model.addAttribute("produtos", produtoService.listarTodos());
        return "movimentacoes/saida";
    }

    @PostMapping("/movimentacoes/saida")
    public String salvarSaida(HttpSession session, Movimentacao movimentacao, Long produtoId,
        RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            movimentacaoService.registrarSaida(movimentacao, produtoId, funcionarioLogadoId(session));
            attributes.addFlashAttribute("msg", "Saida registrada com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/movimentacoes";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/movimentacoes/saida";
        }
    }

    private boolean usuarioNaoLogado(HttpSession session) {
        return session.getAttribute("funcionarioLogadoId") == null;
    }

    private Long funcionarioLogadoId(HttpSession session) {
        Object funcionarioLogadoId = session.getAttribute("funcionarioLogadoId");
        return funcionarioLogadoId == null ? null : (Long) funcionarioLogadoId;
    }
}
