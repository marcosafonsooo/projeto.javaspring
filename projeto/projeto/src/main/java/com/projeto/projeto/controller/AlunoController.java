package com.projeto.projeto.controller;

import com.projeto.projeto.business.AlunoService;
import com.projeto.projeto.infrastructure.entitys.Aluno;
import com.projeto.projeto.infrastructure.entitys.Curso;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AlunoController {

    private final AlunoService alunoService;

    // 🟢 Criar aluno
    @PostMapping
    public ResponseEntity<Aluno> salvarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.salvarAluno(aluno);
        return ResponseEntity.ok(novoAluno);
    }

    // 🟡 Buscar aluno por email
    @GetMapping("/buscar")
    public ResponseEntity<Aluno> buscarAlunoPorEmail(@RequestParam String email) {
        Aluno aluno = alunoService.buscarAlunoPorEmail(email);
        return ResponseEntity.ok(aluno);
    }

    // 🔵 Listar todos os alunos
    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    // 📝 Atualizar aluno por ID
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAlunoPorId(@PathVariable Integer id, @RequestBody Aluno aluno) {
        Aluno atualizado = alunoService.atualizarAlunoPorId(id, aluno);
        return ResponseEntity.ok(atualizado);
    }

    // ❌ Deletar aluno por email
    @DeleteMapping
    public ResponseEntity<Void> deletarAlunoPorEmail(@RequestParam String email) {
        alunoService.deletarAlunoPorEmail(email);
        return ResponseEntity.ok().build();
    }

    // 📚 Matricular aluno em um curso
    @PutMapping("/{alunoId}/matricular/{cursoId}")
    public ResponseEntity<Aluno> matricularEmCurso(@PathVariable Integer alunoId, @PathVariable Integer cursoId) {
        Aluno alunoAtualizado = alunoService.matricularEmCurso(alunoId, cursoId);
        return ResponseEntity.ok(alunoAtualizado);
    }

    // 📋 Listar cursos de um aluno
    @GetMapping("/{alunoId}/cursos")
    public ResponseEntity<List<Curso>> listarCursosDoAluno(@PathVariable Integer alunoId) {
        List<Curso> cursos = alunoService.listarCursosDoAluno(alunoId);
        return ResponseEntity.ok(cursos);
    }

    // 🧍‍♂️ Listar todos os alunos (rota alternativa)
    @GetMapping("/todos")
    public ResponseEntity<List<Aluno>> listarTodosAlunos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    // 🔹 Buscar aluno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Integer id) {
        Aluno aluno = alunoService.buscarAlunoPorId(id);
        return ResponseEntity.ok(aluno);
    }


}
