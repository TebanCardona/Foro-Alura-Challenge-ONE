package com.alura.foro.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.dominio.topico.TopicoPostDTO;
import com.alura.foro.dominio.topico.TopicoPutDTO;
import com.alura.foro.dominio.topico.TopicoResDTO;
import com.alura.foro.services.TopicosService;
import com.alura.foro.utils.res.ResDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
  private TopicosService topicosService;

  public TopicosController(TopicosService topicosService) {
    this.topicosService = topicosService;
  }

  @GetMapping
  public ResponseEntity<List<TopicoResDTO>> listarTopicos() {
    return ResponseEntity.ok(topicosService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicoResDTO> idTopico(@PathVariable long id) {
    return ResponseEntity.ok(topicosService.getById(id));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<TopicoResDTO> regitrarTopicos(@RequestBody @Valid TopicoPostDTO registro,
      UriComponentsBuilder uri) {
    TopicoResDTO topico = topicosService.registrar(registro);
    URI url = uri.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();
    return ResponseEntity.created(url).body(topico);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicoResDTO> actualizarTopicos(@PathVariable long id,
      @RequestBody @Valid TopicoPutDTO datosActualizar) {
    return ResponseEntity.ok(topicosService.editar(id, datosActualizar));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<ResDTO> eliminarTopicos(@PathVariable Long id) {
    return ResponseEntity.ok(topicosService.eliminar(id));
  }
}