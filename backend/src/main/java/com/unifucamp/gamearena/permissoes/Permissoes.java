package com.unifucamp.gamearena.permissoes;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_permissoes")
public class Permissoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false)
    private String name;

    public Permissoes() {
    }

    public Permissoes(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Values {
       public static final String PART_LER = "participante:ler";
       public static final String PART_EDIT = "participante:editar";
       public static final String PART_APROVAR = "participante:aprovar";
       public static final String PART_REJEITAR = "participante:rejeitar";
       public static final String PART_DESATIVAR = "participante:desativar";
       public static final String PART_APROVAR_TODOS = "participante:aprovar:todos";
       public static final String PART_REJEITAR_TODOS = "participante:rejeitar:todos";
    }
}
