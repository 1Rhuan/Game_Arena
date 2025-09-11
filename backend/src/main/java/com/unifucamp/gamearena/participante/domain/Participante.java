package com.unifucamp.gamearena.participante.domain;

import com.unifucamp.gamearena.comprovante.domain.Comprovante;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "apelido")
    private String apelido;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "aceitou_termos", nullable = false)
    private boolean aceitouTermos;

    @Column(name = "status_pagamento")
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "data_atualizacao")
    private LocalDateTime atualizadoEm;

    @OneToOne(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comprovante_id")
    private Comprovante comprovante;

    public Participante() {
    }

    public Participante(Long id, String nomeCompleto, String apelido, String email, LocalDate dataNascimento, boolean aceitouTermos, StatusPagamento statusPagamento, boolean ativo, LocalDateTime criadoEm, LocalDateTime atualizadoEm, Comprovante comprovante) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.aceitouTermos = aceitouTermos;
        this.statusPagamento = statusPagamento;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.comprovante = comprovante;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isAceitouTermos() {
        return aceitouTermos;
    }

    public void setAceitouTermos(boolean aceitouTermos) {
        this.aceitouTermos = aceitouTermos;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public Comprovante getComprovante() {
        return comprovante;
    }

    public void setComprovante(Comprovante comprovante) {
        this.comprovante = comprovante;
    }
}
