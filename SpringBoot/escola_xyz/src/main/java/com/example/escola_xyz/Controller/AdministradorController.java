package com.example.escola_xyz.Controller;

import java.net.http.HttpClient.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.escola_xyz.Model.Administrador;
import com.example.escola_xyz.Repository.AdministradorRepository;
import com.example.escola_xyz.Repository.VerificaCadastroAdmRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class AdministradorController {
    
    // Permite a Transformação de um OBJ-Java em Entidade de DB
    // Cada OBJ criado vira uma Linha do DB
    @Autowired
    AdministradorRepository ar;

    @Autowired
    VerificaCadastroAdmRepository vcar;

    // Verificar acesso e cadastro
    boolean acessoAdm = false;

    // Métodos
    // Para navegar na página de cadastro
    @GetMapping("/cadastrar-adm")
    public String acessoCadastroAdmPage() {
        return "cadastro/cadastro-adm";
    }
    
    // Método para enviar o cadastro do Adm
    @PostMapping("/cadastrar-adm")
    public ModelAndView cadastrarAdmBD(Administrador adm, RedirectAttributes attributes) {
        boolean verificarCpf = vcar.existsById(adm.getCpf()); // Se existir retorna true, caso contrário retorna false
        ModelAndView mv = new ModelAndView("redirect:/login-adm");
        if (verificarCpf){
            // obj adm -> Pega as informações do formulário e cria um obj da classe adm
            ar.save(adm); // Salva no Banco de Dados
            // Criar uma mensagem para o usuário
            String mensagem = "Cadastro Realiado com Sucesso!";
            // Log para o sistema
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg", mensagem); // Leva a mensagem para a tela de view
            attributes.addFlashAttribute("classe","verde");
        } else { // Deu errado, pessoa não pode se cadastrar (Caso o CPF não esteja no Pré-Cadastro)
            String mensagem = "Cadastro não Permitido";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg",mensagem);
            attributes.addFlashAttribute("classe","vermelho");
        }

        return mv;
    }
    
    // Método para acessar (get) página de login do Adm
    @GetMapping("/login-adm")
    public String acessoLoginPageAdm() {
        return "login/login-adm";
    }

    // Método para carregar a página interna após o login
    @PostMapping("acesso-adm")
    public ModelAndView acessoAdm(@RequestParam String cpf, @RequestParam String senha, RedirectAttributes attributes) {
        
        ModelAndView mv = new ModelAndView("redirect:/interna-adm");
        boolean verificaCpf = ar.existsById(cpf); // Verifica se o CPF está cadastrado
        boolean verificaSenha = ar.findByCpf(cpf).getSenha().equals(senha); // Pego o CPF, solicito a senha e comparo com a senha digitada

        if(verificaCpf && verificaSenha){
            acessoAdm = true;
        } else{
            String mensagem = "CPF ou Senha Incorreto";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg",mensagem);
            attributes.addFlashAttribute("classe","vermelho");
            mv.setViewName("redirect:/login-adm");
        }

        return mv;
    }
    
    // Acessar a página interna
    @GetMapping("/interna-adm")
    public ModelAndView acessoInternaPageAdm(RedirectAttributes attributes) {
        String vaiPara = ""; // Endereço do redirecionamento
        if (acessoAdm){ // Verifica se o usuário está logado
            vaiPara = "interna/interna-adm"; // Se estiver vai para a página interna
        } else{ // Caso contrário, nega o acesso e redireciona para o login
            String mensagem = "Acesso não Permitido";
            System.out.println(mensagem);
            attributes.addFlashAttribute("msg",mensagem);
            attributes.addFlashAttribute("classe","vermelha");
            vaiPara = "redirect:/login-adm";
        }
        ModelAndView mv = new ModelAndView(vaiPara); // Model and View, vai direcionar a navegação
        return mv;
    }
    
    @GetMapping("/logout-adm")
    public String logoutAdm() {
        acessoAdm = false;
        return "redirect:/";
    }
    

}
