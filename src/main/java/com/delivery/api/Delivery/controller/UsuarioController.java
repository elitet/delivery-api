package com.delivery.api.Delivery.controller;

import com.delivery.api.Delivery.exception.ApiException;
import com.delivery.api.Delivery.model.Usuario;
import com.delivery.api.Delivery.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {

        Optional<Usuario> usuarioExistente = usuarioService.buscarUsuarioPorEmail(usuario.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new ApiException("Este e-mail já está em uso.");
        }

        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);

        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            throw new ApiException("Usuário não encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {

        Optional<Usuario> usuarioExistente = usuarioService.buscarUsuarioPorId(id);

        if (usuarioExistente.isPresent()) {

            if(!usuarioExistente.get().getEmail().equals(usuarioAtualizado.getEmail())){
                Optional<Usuario> usuarioEmail = usuarioService.buscarUsuarioPorEmail(usuarioAtualizado.getEmail());

                if (usuarioEmail.isPresent()) {
                    throw new ApiException("Este e-mail já está em uso.");
                }
            }

            usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioAtualizado);
            return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
        } else {
            throw new ApiException("Usuário não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {

        Optional<Usuario> usuarioExistente = usuarioService.buscarUsuarioPorId(id);

        if (usuarioExistente.isPresent()) {
            usuarioService.deletarUsuario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ApiException("Usuário não encontrado.");
        }
    }
}

