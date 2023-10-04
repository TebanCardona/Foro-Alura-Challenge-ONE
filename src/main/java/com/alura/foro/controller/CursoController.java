package com.alura.foro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alura.foro.dominio.curso.CursoPutDTO;
import com.alura.foro.dominio.curso.CursoPostDTO;
import com.alura.foro.dominio.curso.CursosResDTO;
import com.alura.foro.services.CursoService;
import com.alura.foro.utils.res.ResDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@ResponseBody
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
  private CursoService cursoService;

  public CursoController(CursoService cursoService) {
    this.cursoService = cursoService;
  }

  @GetMapping
  public ResponseEntity<List<CursosResDTO>> listarCursos() {
    return ResponseEntity.ok(cursoService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CursosResDTO> idCurso(@PathVariable long id) {
    return ResponseEntity.ok(cursoService.getById(id));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<ResDTO> elimiarCurso(@PathVariable long id) {
    return ResponseEntity.ok(cursoService.eliminar(id));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<CursosResDTO> registrarCurso(@RequestBody @Valid CursoPostDTO cursoRegistrarDTO) {
    return ResponseEntity.ok(cursoService.registrar(cursoRegistrarDTO));
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<CursosResDTO> editarCurso(@PathVariable long id,
      @RequestBody @Valid CursoPutDTO cursoActualizarDTO) {
    return ResponseEntity.ok(cursoService.editar(id, cursoActualizarDTO));
  }
}