package com.alura.foro.services;

import org.springframework.stereotype.Service;

import com.alura.foro.dominio.curso.Curso;
import com.alura.foro.dominio.curso.CursoPutDTO;
import com.alura.foro.dominio.curso.CursoPostDTO;
import com.alura.foro.dominio.curso.CursosResDTO;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.utils.errors.ValidacionRechazada;
import com.alura.foro.utils.res.ResDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {
  CursoRepository cursoRepository;

  public CursoService(CursoRepository cursoRepository) {
    this.cursoRepository = cursoRepository;
  }

  public CursosResDTO registrar(CursoPostDTO cursoRegistrarDTO) {
    Curso curso = new Curso(cursoRegistrarDTO);
    cursoRepository.save(curso);
    return new CursosResDTO(curso);
  }

  public List<CursosResDTO> getAll() {
    List<Curso> cursoList = cursoRepository.findAll();
    List<CursosResDTO> cursosResDTOs = new ArrayList<>();
    for (Curso curso : cursoList) {
      cursosResDTOs.add(new CursosResDTO(curso));
    }
    return cursosResDTOs;
  }

  public CursosResDTO getById(Long id) {
    if (!cursoRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el curso con ese Id");
    }
    Curso curso = cursoRepository.getReferenceById(id);
    return new CursosResDTO(curso);
  }

  public CursosResDTO editar(Long id, CursoPutDTO cursoActualizarDTO) {
    if (!cursoRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el curso con ese Id");
    }
    Curso curso = cursoRepository.getReferenceById(id);
    curso.actualizarDatos(cursoActualizarDTO);
    cursoRepository.save(curso);
    return new CursosResDTO(curso);
  }

  public ResDTO eliminar(Long id) {
    if (!cursoRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el curso con ese Id");
    }

    cursoRepository.deleteById(id);
    return new ResDTO("Curso con el id: " + id + " Eliminado Exitosamente!!!");
  }
}