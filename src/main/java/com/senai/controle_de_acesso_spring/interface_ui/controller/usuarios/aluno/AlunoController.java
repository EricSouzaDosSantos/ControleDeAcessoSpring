package com.senai.controle_de_acesso_spring.interface_ui.controller.usuarios.aluno;

import com.senai.controle_de_acesso_spring.application.dto.usuarios.aluno.AlunoDto;
import com.senai.controle_de_acesso_spring.application.service.usuarios.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")

public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Void> cadastrarAluno(@RequestBody AlunoDto alunoDto){
        alunoService.cadastrarAluno(alunoDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> listarAlunosAtivos(){
        return ResponseEntity.ok(alunoService.listarAlunosAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> listarAlunoPorId(@PathVariable Long id){
        return alunoService.buscarAlunoPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> atualizarAluno(@PathVariable Long id, @RequestBody AlunoDto alunoDto){
        if (alunoService.atualizarAluno(id, alunoDto)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarAluno(@PathVariable Long id){
        if (alunoService.inativarAluno(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
