package com.example.estoque_senai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.estoque_senai.Model.Produto;
import com.example.estoque_senai.Service.CategoriaService;
import com.example.estoque_senai.Service.ProdutoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/produtos")
    public String listarProdutos(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/lista";
    }

    @GetMapping("/produtos/novo")
    public String novoProduto(HttpSession session, Model model) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "produtos/cadastro";
    }

    @PostMapping("/produtos/salvar")
    public String salvarProduto(HttpSession session, Produto produto, Long categoriaId, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            produtoService.salvar(produto, categoriaId);
            attributes.addFlashAttribute("msg", "Material salvo com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/produtos";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/produtos/novo";
        }
    }

    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(HttpSession session, @PathVariable Long id, Model model, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        Produto produto = produtoService.buscarPorId(id);
        if (produto == null) {
            attributes.addFlashAttribute("msg", "Material nao encontrado");
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/produtos";
        }
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "produtos/editar";
    }

    @PostMapping("/produtos/atualizar/{id}")
    public String atualizarProduto(HttpSession session, @PathVariable Long id, Produto produto, Long categoriaId,
        RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        produto.setId(id);
        try {
            produtoService.salvar(produto, categoriaId);
            attributes.addFlashAttribute("msg", "Material atualizado com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/produtos";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/produtos/editar/" + id;
        }
    }

    @GetMapping("/produtos/excluir/{id}")
    public String excluirProduto(HttpSession session, @PathVariable Long id, RedirectAttributes attributes) {
        if (usuarioNaoLogado(session)) {
            return "redirect:/login";
        }
        try {
            produtoService.deletar(id);
            attributes.addFlashAttribute("msg", "Material excluido com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
        }
        return "redirect:/produtos";
    }

    private boolean usuarioNaoLogado(HttpSession session) {
        return session.getAttribute("funcionarioLogadoId") == null;
    }
}
