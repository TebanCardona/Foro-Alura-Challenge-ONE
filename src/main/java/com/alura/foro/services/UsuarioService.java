package com.alura.foro.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alura.foro.dominio.usuario.UsuarioPostDTO;
import com.alura.foro.dominio.usuario.UsuarioResDTO;
import com.alura.foro.dominio.usuario.UsuarioPutDTO;
import com.alura.foro.dominio.usuario.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.utils.errors.ValidacionRechazada;
import com.alura.foro.utils.res.ResDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
  UsuarioRepository usuarioRepository;
  private PasswordEncoder passwordEncoder;

  public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UsuarioResDTO registrar(UsuarioPostDTO usuarioPostDto) {
    String password = passwordEncoder.encode(usuarioPostDto.password());
    Usuario usuario = new Usuario(usuarioPostDto, password);
    usuarioRepository.save(usuario);

    return new UsuarioResDTO(usuario);
  }

  public List<UsuarioResDTO> getAll() {
    List<Usuario> usuarioList = usuarioRepository.findAll();
    List<UsuarioResDTO> UsuarioResDTOList = new ArrayList<>();
    for (Usuario u : usuarioList) {
      UsuarioResDTOList.add(new UsuarioResDTO(u));
    }

    return UsuarioResDTOList;
  }

  public UsuarioResDTO getById(Long id) {
    if (!usuarioRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el usuario con ese Id");
    }
    Usuario usuario = usuarioRepository.getReferenceById(id);

    return new UsuarioResDTO(usuario);
  }

  public ResDTO eliminar(Long id) {
    if (!usuarioRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el usuario con ese Id");
    }
    usuarioRepository.deleteById(id);
    return new ResDTO("Usuario con id " + id + " Eliminado Exitosamente!");
  }

  public UsuarioResDTO editar(Long id, UsuarioPutDTO usuarioPutDto) {
    if (!usuarioRepository.existsById(id)) {
      throw new ValidacionRechazada("No existe el usuario con ese Id");
    }
    Usuario usuario = usuarioRepository.getReferenceById(id);
    usuario.actualizarDatos(usuarioPutDto);
    usuarioRepository.save(usuario);
    return new UsuarioResDTO(usuario);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return usuarioRepository.findByEmail(email);
  }
}