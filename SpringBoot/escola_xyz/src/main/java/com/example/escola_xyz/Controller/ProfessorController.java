package com.example.escola_xyz.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {
    





    // Método para acessar (get) página de login do Professor
    @GetMapping("/login-professor")
    public String acessoLoginPageProfessor() {
        return "login/login-professor";
    }
}
