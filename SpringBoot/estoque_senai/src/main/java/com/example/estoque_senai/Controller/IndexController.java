package com.example.estoque_senai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.estoque_senai.Service.AtivoPatrimonialService;
import com.example.estoque_senai.Service.CategoriaService;
import com.example.estoque_senai.Service.FuncionarioService;
import com.example.estoque_senai.Service.MovimentacaoService;
import com.example.estoque_senai.Service.ProdutoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private AtivoPatrimonialService ativoPatrimonialService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("funcionarioLogadoId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("totalProdutos", produtoService.contar());
        model.addAttribute("totalCategorias", categoriaService.contar());
        model.addAttribute("totalMovimentacoes", movimentacaoService.contar());
        model.addAttribute("totalAtivos", ativoPatrimonialService.contar());
        model.addAttribute("totalFuncionarios", funcionarioService.contar());
        model.addAttribute("movimentacoes", movimentacaoService.listarTodos());
        model.addAttribute("produtos", produtoService.listarTodos());
        return "interna/index";
    }
}
