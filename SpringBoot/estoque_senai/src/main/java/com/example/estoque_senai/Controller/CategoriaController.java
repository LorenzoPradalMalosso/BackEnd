package com.example.estoque_senai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.estoque_senai.Model.Categoria;
import com.example.estoque_senai.Service.CategoriaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categorias")
    public String listarCategorias(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias/lista";
    }

    @GetMapping("/categorias/nova")
    public String novaCategoria(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("categoria", new Categoria());
        return "categorias/cadastro";
    }

    @PostMapping("/categorias/salvar")
    public String salvarCategoria(HttpSession session, Categoria categoria, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            categoriaService.salvar(categoria);
            attributes.addFlashAttribute("msg", "Categoria salva com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/categorias";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/categorias/nova";
        }
    }

    @GetMapping("/categorias/editar/{id}")
    public String editarCategoria(HttpSession session, @PathVariable Long id, Model model, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria == null) {
            attributes.addFlashAttribute("msg", "Categoria nao encontrada");
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/categorias";
        }
        model.addAttribute("categoria", categoria);
        return "categorias/editar";
    }

    @PostMapping("/categorias/atualizar/{id}")
    public String atualizarCategoria(HttpSession session, @PathVariable Long id, Categoria categoria,
        RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        categoria.setId(id);
        try {
            categoriaService.salvar(categoria);
            attributes.addFlashAttribute("msg", "Categoria atualizada com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/categorias/editar/" + id;
        }
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/excluir/{id}")
    public String excluirCategoria(HttpSession session, @PathVariable Long id, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            categoriaService.deletar(id);
            attributes.addFlashAttribute("msg", "Categoria excluida com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
        }
        return "redirect:/categorias";
    }

    private boolean usuarioNaoLogado(HttpSession session) {
        return session.getAttribute("funcionarioLogadoId") == null;
    }
}
