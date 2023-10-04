package com.alura.foro.utils.errors;

public class ValidacionRechazada extends RuntimeException {
  public ValidacionRechazada(String s) {
    super(s);
  }
}
