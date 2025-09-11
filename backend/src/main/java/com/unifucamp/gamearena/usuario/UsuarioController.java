package com.unifucamp.gamearena.usuario;

import com.unifucamp.gamearena.usuario.domain.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.unifucamp.gamearena.usuario.dto.CriacaoUsuarioDto;
import com.unifucamp.gamearena.usuario.dto.RespostaUsuarioDto;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid CriacaoUsuarioDto criacaoDeUsuarioDto) {

        log.info("Nova requisição de registro de usuário.");
        usuarioService.createUser(criacaoDeUsuarioDto);

        log.info("Novo registro efetuado com sucesso.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RespostaUsuarioDto>> getAllUsers() {
        log.info("Nova requisição para listar usuários.");
        return ResponseEntity.ok(usuarioService.listUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        log.info("Nova Requisição para deletar um usuario.");
        usuarioService.deleteUser(id);

        log.info("Usuário deletado com sucesso.");
        return ResponseEntity.ok().build();
    }
}
