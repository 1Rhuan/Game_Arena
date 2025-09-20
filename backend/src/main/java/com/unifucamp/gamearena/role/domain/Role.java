package com.unifucamp.gamearena.role.domain;

import com.unifucamp.gamearena.permissoes.Permissoes;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_role_permissoes",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permissoes_id"))
    private Set<Permissoes> permissoes = new HashSet<Permissoes>();

    public Role() {
    }

    public Role(String name, Set<Permissoes> permissoes) {
        this.name = name;
        this.permissoes = permissoes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permissoes> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissoes> permissoes) {
        this.permissoes = permissoes;
    }
}
