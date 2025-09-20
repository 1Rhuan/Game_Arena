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
       public static final String PARTICIPANTE_LER = "participante:ler";
       public static final String PARTICIPANTE_EDITAR = "participante:editar";
       public static final String PARTICIPANTE_APROVAR = "participante:aprovar";
       public static final String PARTICIPANTE_REJEITAR = "participante:rejeitar";
       public static final String PARTICIPANTE_DESATIVAR = "participante:desativar";
       public static final String PARTICIPANTE_APROVAR_TODOS = "participante:aprovar:todos";
       public static final String PARTICIPANTE_REJEITAR_TODOS = "participante:rejeitar:todos";

       public static final String USUARIO_CRIAR = "usuario:criar";
       public static final String USUARIO_EDITAR = "usuario:editar";
       public static final String USUARIO_DELETAR = "usuario:deletar";
    }
}
