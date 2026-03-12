package com.example.escola_xyz.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


// Anotação de Classe Controller (Interagir view e model)
@Controller
public class IndexController {
    

    // Método mais completo
    @GetMapping("/home")
    public ModelAndView acessoHomePage() {
        ModelAndView mv  = new ModelAndView("index");
        return mv;
    }
    
    // Método mais simples de buscar uma página
    @GetMapping("")
    public String acessoHomePage2() {
        return "index";
    }
    
    @GetMapping("/sobre")
    public String acessoSobre() {
        return "pages/sobre";
    }

    @GetMapping("/contato")
    public String acessoContato() {
        return "pages/contato";
    }
    
    
}
