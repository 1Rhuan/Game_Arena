package com.unifucamp.gamearena.comprovante.domain;

import com.unifucamp.gamearena.participante.domain.Participante;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comprovante")
public class Comprovante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_original", nullable = false)
    private String nomeOriginal;

    @Column(name = "nome_salvo", nullable = false)
    private String nomeSalvo;

    @Column(name = "extensao_arquivo", nullable = false)
    private String extensao;

    @Column(name = "caminho_arquivo")
    private String caminho;

    @Column(name = "data_cricao")
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @OneToOne(mappedBy = "comprovante")
    private Participante participante;

    public Long getId() {
        return id;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }

    public String getNomeSalvo() {
        return nomeSalvo;
    }

    public void setNomeSalvo(String nomeSalvo) {
        this.nomeSalvo = nomeSalvo;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}