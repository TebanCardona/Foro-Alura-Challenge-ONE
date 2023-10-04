package com.alura.foro.services;

import java.util.ArrayList;
import java.util.List;

import com.alura.foro.dominio.topico.Topico;
import com.alura.foro.dominio.topico.TopicoPostDTO;
import com.alura.foro.dominio.topico.TopicoPutDTO;
import com.alura.foro.dominio.topico.TopicoResDTO;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.utils.errors.ValidacionRechazada;
import com.alura.foro.utils.res.ResDTO;

public class TopicosService {
  private TopicoRepository topicoRepository;

  public TopicosService(TopicoRepository topicoRepository) {
    this.topicoRepository = topicoRepository;
  }

  public TopicoResDTO registrar(TopicoPostDTO topicoPostDTO) {
    Topico topico = new Topico(topicoPostDTO, topicoPostDTO.usuario(), topicoPostDTO.curso());

    topicoRepository.save(topico);

    return new TopicoResDTO(topico);
  }

  public List<TopicoResDTO> getAll() {
    List<Topico> topicos = topicoRepository.findAll();
    List<TopicoResDTO> topicoResDTOs = new ArrayList<>();
    for (Topico t : topicos) {
      topicoResDTOs.add(new TopicoResDTO(t));
    }
    return topicoResDTOs;
  }

  public TopicoResDTO getById(long id) {
    if (!topicoRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el usuario con ese Id");
    }
    Topico topico = topicoRepository.getReferenceById(id);
    return new TopicoResDTO(topico);
  }

  public ResDTO eliminar(Long id) {
    if (!topicoRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el usuario con ese Id");
    }
    topicoRepository.deleteById(id);
    return new ResDTO("Usuario con id " + id + " Eliminado Exitosamente!");
  }

  public TopicoResDTO editar(Long id, TopicoPutDTO topicoPutDTO) {
    if (!topicoRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el usuario con ese Id");
    }
    Topico topico = topicoRepository.getReferenceById(id);
    topico.actualizaDatos(topicoPutDTO);
    topicoRepository.save(topico);
    return new TopicoResDTO(topico);
  }
}
