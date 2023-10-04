package com.alura.foro.controller;

import com.alura.foro.dominio.usuario.UsuarioPutDTO;
import com.alura.foro.dominio.usuario.UsuarioPostDTO;
import com.alura.foro.dominio.usuario.UsuarioResDTO;
import com.alura.foro.services.UsuarioService;
import com.alura.foro.utils.res.ResDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
  private UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public ResponseEntity<List<UsuarioResDTO>> listarUsuarios() {
    return ResponseEntity.ok(usuarioService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResDTO> idUsuario(@PathVariable long id) {
    return ResponseEntity.ok(usuarioService.getById(id));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<UsuarioResDTO> regitrarUsuario(@RequestBody @Valid UsuarioPostDTO registro,
      UriComponentsBuilder uri) {
    UsuarioResDTO usuario = usuarioService.registrar(registro);
    URI url = uri.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();
    return ResponseEntity.created(url).body(usuario);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<UsuarioResDTO> actualizarUsuario(@PathVariable long id,
      @RequestBody @Valid UsuarioPutDTO datosActualizar) {
    return ResponseEntity.ok(usuarioService.editar(id, datosActualizar));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<ResDTO> eliminarUsuario(@PathVariable Long id) {
    return ResponseEntity.ok(usuarioService.eliminar(id));
  }
}