package com.alura.foro.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alura.foro.dominio.usuario.Usuario;
import com.alura.foro.dominio.usuario.UsuarioAuthDTO;
import com.alura.foro.services.TokenService;
import com.alura.foro.utils.security.DatosJWTtoken;

@RestController
@RequestMapping("/login")
public class LoginController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  private ResponseEntity<DatosJWTtoken> autenticarUsuario(@RequestBody @Valid UsuarioAuthDTO usuarioAuthDTO) {
    Authentication authToken = new UsernamePasswordAuthenticationToken(usuarioAuthDTO.email(),
        usuarioAuthDTO.password());
    var usuarioAutenticado = authenticationManager.authenticate(authToken);
    String JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
    return ResponseEntity.ok(new DatosJWTtoken(JWTToken));
  }

}