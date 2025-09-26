package com.projeto.projeto.business;

import com.projeto.projeto.infrastructure.entitys.Aluno;
import com.projeto.projeto.infrastructure.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public void salvarAluno(Aluno Aluno){
        repository.saveAndFlush(Aluno);
    }

    public Aluno buscarAlunoPorEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    public void deletarAlunoPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarAlunoPorId(Integer id, Aluno Aluno){
        Aluno AlunoEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Atualiza apenas os campos não nulos
        if (Aluno.getEmail() != null) {
            AlunoEntity.setEmail(Aluno.getEmail());
        }
        if (Aluno.getNome() != null) {
            AlunoEntity.setNome(Aluno.getNome());
        }
        if (Aluno.getSenha() != null) {
            AlunoEntity.setSenha(Aluno.getSenha());
        }

        repository.saveAndFlush(AlunoEntity);
    }
}
