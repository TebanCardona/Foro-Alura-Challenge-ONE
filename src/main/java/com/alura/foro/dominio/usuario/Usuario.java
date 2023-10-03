package com.alura.foro.dominio.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity(name = "usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nombre;
  private String email;
  private String password;

  public Usuario(@NotNull @Valid Usuario usuario) {
    this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
  }

  public Usuario(@NotNull DTORegistrarUsuario usuario) {
    this.nombre = usuario.nombre();
    this.email = usuario.email();
    this.password = usuario.password();
  }

  public Usuario(String nombre, String email) {
    this.nombre = nombre;
    this.email = email;
  }

  public Usuario(Long id, String nombre, String email) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Usuario other = (Usuario) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return nombre;
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

  public void actualizarDatos(@Valid DTOActualizarUsuario datosActualizar) {
    if (datosActualizar.nombre() != null) {
      this.nombre = datosActualizar.nombre();
    }
    if (datosActualizar.email() != null) {
      this.email = datosActualizar.email();
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getpassword() {
    return password;
  }

  public void setpassword(String password) {
    this.password = password;
  }

}