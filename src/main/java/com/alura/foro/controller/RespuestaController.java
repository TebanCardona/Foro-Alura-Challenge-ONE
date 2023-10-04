package com.alura.foro.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alura.foro.dominio.respuesta.RespuestaEditDTO;
import com.alura.foro.dominio.respuesta.RespuestaPostDTO;
import com.alura.foro.dominio.respuesta.RespuestaResDTO;
import com.alura.foro.services.RespuestaService;
import com.alura.foro.utils.res.ResDTO;

@Controller
@ResponseBody
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
  private final RespuestaService respuestaService;

  public RespuestaController(RespuestaService respuestaService) {
    this.respuestaService = respuestaService;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<RespuestaResDTO> registrarRespuesta(@RequestBody @Valid RespuestaPostDTO respuestaPostDTO) {
    RespuestaResDTO respuesta = respuestaService.registrar(respuestaPostDTO);
    return ResponseEntity.ok(respuesta);
  }

  @GetMapping
  public ResponseEntity<List<RespuestaResDTO>> listarCursos() {
    return ResponseEntity.ok(respuestaService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaResDTO> listarRespuestaPorId(@PathVariable Long id) {
    return ResponseEntity.ok(respuestaService.getById(id));
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<RespuestaResDTO> editarRespuesta(@PathVariable Long id,
      @RequestBody RespuestaEditDTO respuestaEditDTO) {
    return ResponseEntity.ok(respuestaService.editar(id, respuestaEditDTO));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<ResDTO> eliminarRespuesta(@PathVariable Long id) {
    return ResponseEntity.ok(respuestaService.eliminar(id));
  }
}
