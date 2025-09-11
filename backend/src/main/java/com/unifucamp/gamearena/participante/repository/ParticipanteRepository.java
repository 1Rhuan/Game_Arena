package com.unifucamp.gamearena.participante.repository;

import com.unifucamp.gamearena.participante.domain.Participante;
import com.unifucamp.gamearena.participante.domain.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Optional<Participante> findByEmail(String email);
    Page<Participante> findByStatusPagamento(StatusPagamento statusPagamento, Pageable pageable);
}
