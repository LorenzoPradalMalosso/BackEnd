package com.example.escola_xyz.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoController {
    



    

    // Método para acessar (get) página de login do Aluno
    @GetMapping("/login-aluno")
    public String acessoLoginPageAluno() {
        return "login/login-aluno";
    }

}
