package com.alura.foro.services;

import org.springframework.stereotype.Service;

import com.alura.foro.dominio.respuesta.Respuesta;
import com.alura.foro.dominio.respuesta.RespuestaEditDTO;
import com.alura.foro.dominio.respuesta.RespuestaPostDTO;
import com.alura.foro.dominio.respuesta.RespuestaResDTO;
import com.alura.foro.dominio.topico.Topico;
import com.alura.foro.dominio.usuario.Usuario;
import com.alura.foro.repository.RespuestaRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.utils.errors.ValidacionRechazada;
import com.alura.foro.utils.res.ResDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class RespuestaService {
  private final RespuestaRepository respuestaRepository;
  private final TopicoRepository topicoRepository;
  private final UsuarioRepository usuarioRepository;

  public RespuestaService(RespuestaRepository respuestaRepository, TopicoRepository topicoRepository,
      UsuarioRepository usuarioRepository) {
    this.respuestaRepository = respuestaRepository;
    this.topicoRepository = topicoRepository;
    this.usuarioRepository = usuarioRepository;
  }

  public RespuestaResDTO registrar(RespuestaPostDTO respuestaPostDTO) {
    if (!topicoRepository.findById(respuestaPostDTO.topico_id()).isPresent()) {
      throw new ValidacionRechazada("No existe el topico con ese id");
    }
    if (!usuarioRepository.existsById(respuestaPostDTO.autor_id())) {
      throw new ValidacionRechazada("No existe el autor con ese id");
    }

    Topico topico = topicoRepository.getReferenceById(respuestaPostDTO.topico_id());
    Usuario usuario = usuarioRepository.getReferenceById(respuestaPostDTO.topico_id());

    Respuesta respuesta = new Respuesta(respuestaPostDTO, topico, usuario);

    respuestaRepository.save(respuesta);
    return new RespuestaResDTO(respuesta);
  }

  public List<RespuestaResDTO> getAll() {
    List<Respuesta> respuestaList = respuestaRepository.findAll();
    List<RespuestaResDTO> respuestaDTOs = new ArrayList<>();
    for (Respuesta r : respuestaList) {
      respuestaDTOs.add(new RespuestaResDTO(r));
    }
    return respuestaDTOs;
  }

  public RespuestaResDTO getById(Long id) {
    if (!respuestaRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe la Respuesta con ese id");
    }
    Respuesta respuesta = respuestaRepository.getReferenceById(id);
    return new RespuestaResDTO(respuesta);
  }

  public RespuestaResDTO editar(Long id, RespuestaEditDTO respuestaEditDTO) {
    if (!respuestaRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe la Respuesta con ese id");
    }
    Respuesta respuesta = respuestaRepository.getReferenceById(id);
    respuesta.actualizarDatos(respuestaEditDTO);
    return new RespuestaResDTO(respuesta);
  }

  public ResDTO eliminar(Long id) {
    if (!respuestaRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe la Respuesta con ese id");
    }
    respuestaRepository.deleteById(id);
    return new ResDTO("Usuario con id " + id + " Eliminado Exitosamente!");
  }
}