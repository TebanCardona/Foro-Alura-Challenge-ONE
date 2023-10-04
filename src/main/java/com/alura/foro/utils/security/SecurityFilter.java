package com.alura.foro.utils.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.services.TokenService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  private final TokenService tokenService;
  private final UsuarioRepository usuarioRepository;

  public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // Obtener el token del header
    var authHeader = request.getHeader("Authorization");
    if (authHeader != null) {
      var token = authHeader.replace("Bearer ", "");
      var email = tokenService.getSubject(token); // extract username
      if (email != null) {
        // Token valido
        var usuario = usuarioRepository.findByEmail(email);
        var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
            usuario.getAuthorities()); // Forzamos un inicio de sesion
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }
}