package com.unifucamp.gamearena.permissoes.repository;

import com.unifucamp.gamearena.permissoes.Permissoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissoesRepository extends JpaRepository<Permissoes,Long> {
    List<Permissoes> name(String name);

    Optional<Permissoes> findByName(String name);
}
