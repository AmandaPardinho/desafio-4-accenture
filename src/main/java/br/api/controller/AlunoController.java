package br.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.api.dto.AlunoDto;
import br.api.service.AlunoService;
import feign.FeignException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/aluno")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

    @Autowired
    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/alunos")
    @ResponseStatus(HttpStatus.OK)
    public List<AlunoDto> getCadastros() {
        return alunoService.listarCadastros();
    }

    @GetMapping("/aluno/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlunoDto getCadastroById(Long id) {
        return alunoService.cadastroById(id);
    }

    @PostMapping(value = "/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> insertAluno(@ModelAttribute AlunoDto alunoDto) { 
        logger.info("Recebendo requisição para criar aluno: {}", alunoDto);

        try {
            alunoService.insertAluno(alunoDto);
            logger.info("Aluno criado com sucesso: {}", alunoDto);
            return ResponseEntity.created(null).body("Aluno criado com sucesso: " + alunoDto);
        } catch (FeignException e) {
            logger.error("Erro ao consultar o CEP: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao consultar o CEP: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Erro interno no servidor: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        } 
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateAluno(@RequestBody AlunoDto alunoDto) {
        alunoService.updateAluno(alunoDto);
        return "Usuario atualizado com sucesso!";
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAluno(Long id) {
        alunoService.deleteAluno(id);
        return "Usuario deletado com sucesso!";
    }


}
