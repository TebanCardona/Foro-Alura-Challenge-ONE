package com.alura.foro.services;

import com.alura.foro.dominio.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String apiSecret;

  public String generarToken(Usuario usuario) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      return JWT.create()
          .withIssuer("foro_alura")
          .withSubject(usuario.getEmail())
          .withClaim("id", usuario.getId())
          .withExpiresAt(generarFechaExpiracion())
          .sign(algorithm);
    } catch (JWTCreationException ex) {
      throw new RuntimeException(ex);
    }
  }

  public String getSubject(String token) {
    if (token == null)
      throw new RuntimeException("Token Invalido");
    DecodedJWT decodedJWT;
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      decodedJWT = JWT.require(algorithm)
          .withIssuer("foro_alura")
          .build().verify(token);
      decodedJWT.getSubject();
    } catch (JWTVerificationException ex) {
      throw new RuntimeException(ex);
    }
    if (decodedJWT.getSubject() == null)
      throw new RuntimeException("Verificacion invalida");
    return decodedJWT.getSubject();
  }

  private Instant generarFechaExpiracion() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
  }
}