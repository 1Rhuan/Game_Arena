package com.unifucamp.gamearena.usuario.domain;

import com.unifucamp.gamearena.infra.exception.ConflictException;
import com.unifucamp.gamearena.infra.exception.NotFoundException;
import com.unifucamp.gamearena.role.domain.Role;
import com.unifucamp.gamearena.role.domain.RoleService;
import com.unifucamp.gamearena.usuario.UsuarioRepository;
import com.unifucamp.gamearena.usuario.dto.CriacaoUsuarioDto;
import com.unifucamp.gamearena.usuario.dto.RespostaUsuarioDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    public void createUser(CriacaoUsuarioDto criacaoDeUsuarioDto) {

        Optional<usuario> user = usuarioRepository.findByEmail(criacaoDeUsuarioDto.email());

        if (user.isPresent()) {
            log.warn("Falha na criação de usuário. Email já em uso");
            throw new ConflictException("E-mail já em uso.");
        }

        String password = passwordEncoder.encode(criacaoDeUsuarioDto.password());
        Role role = roleService.findRole(criacaoDeUsuarioDto.role());

        usuario newUsuario = new usuario(
                criacaoDeUsuarioDto.email(),
                password,
                List.of(role));

        log.info("Novo usuário criado.");
        usuarioRepository.save(newUsuario);
    }

    public List<RespostaUsuarioDto> listUsers() {

        List<usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuario -> new RespostaUsuarioDto(
                        usuario.getId(),
                        usuario.getEmail()))
                .collect(Collectors.toList());
    }

    public void deleteUser(String id) {

        log.warn("Solicitação de exclusão ID: {}", id);

        Optional<usuario> user = usuarioRepository.findById(id);

        if (user.isEmpty()) {
            log.warn("Usuário inválido.");
            throw new NotFoundException("Usuário inválido.");
        }

        log.info("Usuário deletado: {}", id);
        usuarioRepository.deleteById(id);
    }

}
