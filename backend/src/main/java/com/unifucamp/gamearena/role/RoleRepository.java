package com.unifucamp.gamearena.role;

import com.unifucamp.gamearena.role.domain.Role;
import com.unifucamp.gamearena.role.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
