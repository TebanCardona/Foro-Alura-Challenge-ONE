package com.alura.foro.dominio.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String email;
  private String password;

  public Usuario(UsuarioPostDTO usuarioRegistroDTO, String password) {
    this.nombre = usuarioRegistroDTO.nombre();
    this.email = usuarioRegistroDTO.email();
    this.password = password;
  }

  public void actualizarDatos(UsuarioPutDTO usuarioUpdateDTO) {
    if (usuarioUpdateDTO.nombre() != null) {
      this.nombre = usuarioUpdateDTO.nombre();
    }
    if (usuarioUpdateDTO.password() != null) {
      this.password = usuarioUpdateDTO.password();
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}