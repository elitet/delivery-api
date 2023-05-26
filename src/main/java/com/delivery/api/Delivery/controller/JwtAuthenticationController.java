package com.delivery.api.Delivery.controller;

import com.delivery.api.Delivery.auth.JwtTokenUtil;
import com.delivery.api.Delivery.dto.JwtRequest;
import com.delivery.api.Delivery.dto.JwtResponse;
import com.delivery.api.Delivery.exception.ApiException;
import com.delivery.api.Delivery.model.Usuario;
import com.delivery.api.Delivery.repository.UsuarioRepository;
import com.delivery.api.Delivery.service.JwtUserDetailsService;
import com.delivery.api.Delivery.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Usuario signUpRequest) {

        if(!usuarioService.buscarUsuarioPorEmail(signUpRequest.getEmail()).isEmpty()) {
            throw new ApiException("O e-mail informado já está em uso.");
        }

        // Criar conta de usuário
        Usuario usuario = Usuario.builder()
                .nome(signUpRequest.getNome())
                .email(signUpRequest.getEmail())
                .senha(signUpRequest.getSenha())
                .build();

        usuarioService.cadastrarUsuario(usuario);

        return ResponseEntity.ok(usuario);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
