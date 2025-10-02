package com.projeto.projeto.infrastructure.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "descricao", length = 300)
    private String descricao;

    @ManyToMany(mappedBy = "cursos")
    @JsonIgnore  // 🔥 evita loop recursivo Aluno → Curso → Aluno → ...
    private List<Aluno> alunos = new ArrayList<>();
}
