package com.projeto.projeto.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "aluno")
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email", unique = true, length = 200)
    private String email;

    @Column(name = "nome", length = 200)
    private String nome;

    @Column(name = "senha", length = 200) // 👈 novo campo
    private String senha;
}
