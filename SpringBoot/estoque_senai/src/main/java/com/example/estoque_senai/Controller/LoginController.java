package com.example.estoque_senai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.estoque_senai.Model.Funcionario;
import com.example.estoque_senai.Service.FuncionarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("funcionarioLogadoId") != null) {
            return "redirect:/dashboard";
        }
        return "login/login";
    }

    @PostMapping("/login")
    public String entrar(String nif, String senha, HttpSession session, RedirectAttributes attributes) {
        try {
            Funcionario funcionario = funcionarioService.autenticar(nif, senha);
            session.setAttribute("funcionarioLogadoId", funcionario.getId());
            session.setAttribute("funcionarioLogadoNome", funcionario.getNome());
            session.setAttribute("funcionarioLogadoNif", funcionario.getNif());
            attributes.addFlashAttribute("msg", "Login realizado com sucesso");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/dashboard";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/login";
        }
    }

    @GetMapping("/cadastro")
    public String cadastro(HttpSession session) {
        if (session.getAttribute("funcionarioLogadoId") != null) {
            return "redirect:/dashboard";
        }
        return "funcionarios/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(Funcionario funcionario, RedirectAttributes attributes) {
        try {
            funcionarioService.cadastrar(funcionario);
            attributes.addFlashAttribute("msg", "Cadastro realizado com sucesso. Faca o login para continuar");
            attributes.addFlashAttribute("classe", "sucesso");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("msg", e.getMessage());
            attributes.addFlashAttribute("classe", "erro");
            return "redirect:/cadastro";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes attributes) {
        session.invalidate();
            attributes.addFlashAttribute("msg", "Logout realizado com sucesso");
        attributes.addFlashAttribute("classe", "sucesso");
        return "redirect:/login";
    }
}
