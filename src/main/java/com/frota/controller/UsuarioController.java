package com.frota.controller;

import com.frota.dto.LoginRequestDTO;
import com.frota.dto.UsuarioDTO;
import com.frota.model.Usuario;
import com.frota.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; //

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody Usuario usuario) {
        // Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Usuario salvo = repository.save(usuario);

        // Retorna o DTO (sem a senha!)
        UsuarioDTO dto = new UsuarioDTO(salvo.getId(), salvo.getNome(), salvo.getEmail(), salvo.getPerfil().name());        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return repository.findAll().stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), u.getPerfil().name()))                .toList();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dados) {
        // 1. Busca o usuário pelo e-mail
        return repository.findByEmail(dados.email())
                .map(usuario -> {
                    // 2. Compara a senha digitada com a senha criptografada do banco
                    if (passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
                        // Se der certo, retorna os dados do usuário (sem a senha)
                        return ResponseEntity.ok(new UsuarioDTO(
                                usuario.getId(),
                                usuario.getNome(),
                                usuario.getEmail(),
                                usuario.getPerfil().name()
                        ));
                    }
                    // Senha incorreta
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida");
                })
                // Usuário não encontrado
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado"));
    }
}

