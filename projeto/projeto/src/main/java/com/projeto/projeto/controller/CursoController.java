package com.projeto.projeto.controller;

import com.projeto.projeto.infrastructure.entitys.Curso;
import com.projeto.projeto.infrastructure.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@CrossOrigin("*")
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // 📋 Listar cursos
    @GetMapping
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    // 🟢 Criar curso
    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    // 🔵 Buscar por ID
    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Integer id) {
        return cursoRepository.findById(id).orElseThrow();
    }

    // 📝 Atualizar curso por ID
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Integer id, @RequestBody Curso dadosAtualizados) {
        return cursoRepository.findById(id)
                .map(cursoExistente -> {
                    if (dadosAtualizados.getNome() != null) {
                        cursoExistente.setNome(dadosAtualizados.getNome());
                    }
                    if (dadosAtualizados.getDescricao() != null) {
                        cursoExistente.setDescricao(dadosAtualizados.getDescricao());
                    }
                    Curso cursoAtualizado = cursoRepository.save(cursoExistente);
                    return ResponseEntity.ok(cursoAtualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ❌ Deletar curso por ID
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        cursoRepository.deleteById(id);
    }
}
