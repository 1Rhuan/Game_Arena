package com.unifucamp.gamearena.usuario;

import com.unifucamp.gamearena.usuario.domain.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<usuario, String> {
    Optional<usuario> findByEmail(String email);
}
