package com.projeto.projeto.business;

import com.projeto.projeto.infrastructure.entitys.Usuario;
import com.projeto.projeto.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
        Usuario usuarioEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Atualiza apenas os campos não nulos
        if (usuario.getEmail() != null) {
            usuarioEntity.setEmail(usuario.getEmail());
        }
        if (usuario.getNome() != null) {
            usuarioEntity.setNome(usuario.getNome());
        }
        if (usuario.getSenha() != null) {
            usuarioEntity.setSenha(usuario.getSenha());
        }

        repository.saveAndFlush(usuarioEntity);
    }
}
