package com.projeto.projeto.controller;

import com.projeto.projeto.business.AlunoService;
import com.projeto.projeto.infrastructure.entitys.Aluno;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Void> salvarAluno(@RequestBody Aluno Aluno){
        alunoService.salvarAluno(Aluno);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Aluno> buscarAlunoPorEmail(@RequestParam String email){
        return ResponseEntity.ok(alunoService.buscarAlunoPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAlunoPorEmail(@RequestParam String email){
        alunoService.deletarAlunoPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarAlunoPorId(@RequestParam Integer id, @RequestBody Aluno Aluno){
        alunoService.atualizarAlunoPorId(id, Aluno);
        return ResponseEntity.ok().build();
    }
}
