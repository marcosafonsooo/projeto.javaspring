package com.projeto.projeto.business;

import com.projeto.projeto.infrastructure.entitys.Aluno;
import com.projeto.projeto.infrastructure.entitys.Curso;
import com.projeto.projeto.infrastructure.repository.AlunoRepository;
import com.projeto.projeto.infrastructure.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    // 🟢 Criar aluno
    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.saveAndFlush(aluno);
    }

    // 🟡 Buscar por email
    public Aluno buscarAlunoPorEmail(String email) {
        return alunoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    // 🔵 Listar todos
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    // 📝 Atualizar por ID
    public Aluno atualizarAlunoPorId(Integer id, Aluno dadosAtualizados) {
        Aluno alunoEntity = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

        if (dadosAtualizados.getEmail() != null) alunoEntity.setEmail(dadosAtualizados.getEmail());
        if (dadosAtualizados.getNome() != null) alunoEntity.setNome(dadosAtualizados.getNome());
        if (dadosAtualizados.getSenha() != null) alunoEntity.setSenha(dadosAtualizados.getSenha());

        return alunoRepository.saveAndFlush(alunoEntity);
    }

    // ❌ Deletar por email
    public void deletarAlunoPorEmail(String email) {
        alunoRepository.deleteByEmail(email);
    }

    // 📚 Matricular em curso
    public Aluno matricularEmCurso(Integer alunoId, Integer cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        aluno.getCursos().add(curso);
        return alunoRepository.saveAndFlush(aluno);
    }

    // 📋 Listar cursos de um aluno
    public List<Curso> listarCursosDoAluno(Integer alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return aluno.getCursos();
    }

    // 🧍‍♂️ Listar todos (rota alternativa)
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
}
