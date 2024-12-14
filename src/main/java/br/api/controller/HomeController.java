package br.api.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import br.api.dto.AlunoDto;

/**
 * Controller for handling home and login routes.
 */
@Controller
public class HomeController {

    /**
     * Handle GET requests to /home.
     * @return View name for home page
     */
    @GetMapping("/api/aluno/insert")
    public String home(Model model) {
        model.addAttribute("aluno", new AlunoDto());
        return "aluno"; // Maps to home.html
    }

    /**
     * Handle GET requests to /api/cep.
     * @param cep The CEP to search for
     * @return Address data
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/api/cep")
    @ResponseBody
    public Map<String, String> getCep(@RequestParam String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, Map.class);
    }

    @RequestMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

    /**
     * Handle GET requests to /login.
     * @return View name for login page
     */
    @GetMapping("/login")
    public String login() {
        return "login"; // Maps to login.html
    }
}