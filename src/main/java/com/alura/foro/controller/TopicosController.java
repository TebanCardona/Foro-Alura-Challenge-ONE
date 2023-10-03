package com.alura.foro.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.dominio.curso.Curso;
import com.alura.foro.dominio.topico.DTOActualizarTopico;
import com.alura.foro.dominio.topico.DTOListarTopicos;
import com.alura.foro.dominio.topico.DTORegistrarTopico;
import com.alura.foro.dominio.topico.DTORespuestaTopico;
import com.alura.foro.dominio.topico.Topico;
import com.alura.foro.dominio.usuario.Usuario;
import com.alura.foro.repository.TopicoRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

  @Autowired
  private TopicoRepository topicoRepository;

  @PostMapping
  public ResponseEntity<DTORespuestaTopico> registrarTopico(@RequestBody @Valid DTORegistrarTopico registro,
      UriComponentsBuilder uri) {
    System.out.println("El request llega correctamente!");
    System.out.println(registro);
    Topico topico = topicoRepository.save(new Topico(registro));
    DTORespuestaTopico datosDeRespuestaTopico = new DTORespuestaTopico(
        topico.getId(),
        topico.getTitulo(),
        topico.getMensaje(),
        topico.getfechaCreacion(),
        topico.getStatus(),
        new Usuario(
            topico.getUsuario().getId(),
            topico.getUsuario().getNombre(),
            topico.getUsuario().getEmail(),
            topico.getUsuario().getPassword()),
        new Curso(
            topico.getCurso().getId(),
            topico.getCurso().getNombre(),
            topico.getCurso().getCategoria()));
    URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(url).body(datosDeRespuestaTopico);
  }

  @GetMapping
  public ResponseEntity<Page<DTOListarTopicos>> listadoMedicos(Pageable paginacion) {
    return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DTOListarTopicos::new));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DTORespuestaTopico> listarTopicosPorId(@PathVariable Long id) {
    Topico topico = topicoRepository.getReferenceById(id);
    var datosTopico = new DTORespuestaTopico(
        topico.getId(),
        topico.getTitulo(),
        topico.getMensaje(),
        topico.getfechaCreacion(),
        topico.getStatus(),
        new Usuario(
            topico.getUsuario().getId(),
            topico.getUsuario().getNombre(),
            topico.getUsuario().getEmail(),
            topico.getUsuario().getPassword()),
        new Curso(
            topico.getCurso().getId(),
            topico.getCurso().getNombre(),
            topico.getCurso().getCategoria()));
    return ResponseEntity.ok(datosTopico);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DTORespuestaTopico> actualizarTopico(@RequestBody @Valid DTOActualizarTopico datosActualizar) {
    Topico topico = topicoRepository.getReferenceById(datosActualizar.id());
    topico.actualizarDatos(datosActualizar);
    return ResponseEntity.ok(new DTORespuestaTopico(
        topico.getId(),
        topico.getTitulo(),
        topico.getMensaje(),
        topico.getfechaCreacion(),
        topico.getStatus(),
        new Usuario(
            topico.getUsuario().getId(),
            topico.getUsuario().getNombre(),
            topico.getUsuario().getEmail(),
            topico.getUsuario().getpassword()),
        new Curso(
            topico.getCurso().getId(),
            topico.getCurso().getNombre(),
            topico.getCurso().getCategoria())));
  }

  // **** DELETE ****
  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity eliminarTopico(@PathVariable Long id) {
    Topico topico = topicoRepository.getReferenceById(id);
    topicoRepository.delete(topico);
    return ResponseEntity.noContent().build();
  }
}