package com.alura.foro.utils.errors;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JavaErrors {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> tratarError404() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<DatosErrorValidacion>> error400(MethodArgumentNotValidException ex) {
    List<DatosErrorValidacion> errres = ex.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
    return ResponseEntity.badRequest().body(errres);
  }

  @ExceptionHandler(ValidacionRechazada.class)
  public ResponseEntity<String> tratarErrorValidacion(ValidacionRechazada ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  private record DatosErrorValidacion(String campo, String Error) {
    public DatosErrorValidacion(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }

}