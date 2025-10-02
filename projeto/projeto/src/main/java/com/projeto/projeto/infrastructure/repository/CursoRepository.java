package com.projeto.projeto.infrastructure.repository;

import com.projeto.projeto.infrastructure.entitys.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
