package com.meugrupo.cadastro_usuario.infrastructure.repository;

import com.meugrupo.cadastro_usuario.infrastructure.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositoy extends JpaRepository<Usuario, Integer> {

     Optional<Usuario> findByEmail(String email);

     @Transactional
     void deleteByEmail(String email);
}
