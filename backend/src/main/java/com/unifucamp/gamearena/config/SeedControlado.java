package com.unifucamp.gamearena.config;

import com.unifucamp.gamearena.permissoes.Permissoes;
import com.unifucamp.gamearena.permissoes.repository.PermissoesRepository;
import com.unifucamp.gamearena.role.domain.Role;
import com.unifucamp.gamearena.role.repository.RoleRepository;
import com.unifucamp.gamearena.usuario.domain.Usuario;
import com.unifucamp.gamearena.usuario.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static com.unifucamp.gamearena.permissoes.Permissoes.Values.*;

@Configuration
public class SeedControlado implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SeedControlado.class);

    private final UsuarioRepository usuarioRepository;
    private final PermissoesRepository permissoesRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SeedControlado(UsuarioRepository usuarioRepository,
                          PermissoesRepository permissoesRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.permissoesRepository = permissoesRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Iniciando seed controlado");

        // Permissões
        var partLer = garantirPermissoes(PARTICIPANTE_LER);
        var partEditar = garantirPermissoes(PARTICIPANTE_EDITAR);
        var partAprovar = garantirPermissoes(PARTICIPANTE_APROVAR);
        var partRejeitar = garantirPermissoes(PARTICIPANTE_REJEITAR);
        var partDesativar = garantirPermissoes(PARTICIPANTE_DESATIVAR);
        var partAprovarTodos = garantirPermissoes(PARTICIPANTE_APROVAR_TODOS);
        var partRejeitarTodos = garantirPermissoes(PARTICIPANTE_REJEITAR_TODOS);
        var usuaCriar = garantirPermissoes(USUARIO_CRIAR);
        var usuaEditar = garantirPermissoes(USUARIO_EDITAR);
        var usuaDeletar = garantirPermissoes(USUARIO_DELETAR);

        // Cargos
        var roleConvidado = garantirRole("CONVIDADO", Set.of(partLer));
        var roleEquipe = garantirRole("EQUIPE", Set.of(partLer, partEditar, partAprovar, partRejeitar));
        var roleAdmin = garantirRole("ADMIN", Set.of(partLer, partEditar, partAprovar, partRejeitar, partDesativar, partAprovarTodos, partRejeitarTodos, usuaCriar, usuaEditar, usuaDeletar));

        // Usuarios

        var UsuarioAdmin = garantirUsuario("admin@admin.com", "admin123", roleAdmin);

        logger.info("Seed controlado completo com sucesso");
    }

    private Permissoes garantirPermissoes(String name) {
        return permissoesRepository.findByName(name)
                .orElseGet(() -> permissoesRepository.save(new Permissoes(name)));
    }

    private Role garantirRole(String name, Set<Permissoes> permissoes) {
       return roleRepository.findByName(name)
               .map(roleExistente -> {
                   roleExistente.setPermissoes(permissoes);
                   return roleRepository.save(roleExistente);
               })
               .orElseGet(() -> roleRepository.save(new Role(name, permissoes)));
    }

    private Usuario garantirUsuario(String email, String password, Role role) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioExistente -> {
                    usuarioExistente.setPassword(passwordEncoder.encode(password));
                    usuarioExistente.setRoles(Set.of(role));
                    return usuarioRepository.save(usuarioExistente);
                })
                .orElseGet(() -> usuarioRepository.save(new Usuario(email, password, Set.of(role))));
    }
}
