package com.example.estoque_senai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.estoque_senai.Model.AtivoPatrimonial;
import com.example.estoque_senai.Service.AtivoPatrimonialService;
import com.example.estoque_senai.Service.CategoriaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AtivoPatrimonialController {

    @Autowired
    private AtivoPatrimonialService ativoPatrimonialService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/ativos")
    public String listarAtivos(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("ativos", ativoPatrimonialService.listarTodos());
        return "ativos/lista";
    }

    @GetMapping("/ativos/novo")
    public String novoAtivo(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("ativo", new AtivoPatrimonial());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "ativos/cadastro";
    }

    @PostMapping("/ativos/salvar")
    public String salvarAtivo(HttpSession session, AtivoPatrimonial ativoPatrimonial, Long categoriaId,
        RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            ativoPatrimonialService.salvar(ativoPatrimonial, categoriaId);
            attributes.addFlashAttribute("msg", "Ativo patrimonial salvo com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/ativos";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/ativos/novo";
        }
    }

    @GetMapping("/ativos/editar/{id}")
    public String editarAtivo(HttpSession session, @PathVariable Long id, Model model, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        AtivoPatrimonial ativoPatrimonial = ativoPatrimonialService.buscarPorId(id);
        if (ativoPatrimonial == null) {
            attributes.addFlashAttribute("msg", "Ativo patrimonial nao encontrado");
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/ativos";
        }
        model.addAttribute("ativo", ativoPatrimonial);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "ativos/editar";
    }

    @PostMapping("/ativos/atualizar/{id}")
    public String atualizarAtivo(HttpSession session, @PathVariable Long id, AtivoPatrimonial ativoPatrimonial,
        Long categoriaId, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        ativoPatrimonial.setId(id);
        try {
            ativoPatrimonialService.salvar(ativoPatrimonial, categoriaId);
            attributes.addFlashAttribute("msg", "Ativo patrimonial atualizado com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/ativos";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/ativos/editar/" + id;
        }
    }

    @GetMapping("/ativos/excluir/{id}")
    public String excluirAtivo(HttpSession session, @PathVariable Long id, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            ativoPatrimonialService.deletar(id);
            attributes.addFlashAttribute("msg", "Ativo patrimonial excluido com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
        }
        return "redirect:/ativos";
    }

    private boolean usuarioNaoLogado(HttpSession session) {
        return session.getAttribute("funcionarioLogadoId") == null;
    }
}
