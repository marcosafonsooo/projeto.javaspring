package com.projeto.projeto.infrastructure.repository;

import com.projeto.projeto.infrastructure.entitys.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Optional<Aluno> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
