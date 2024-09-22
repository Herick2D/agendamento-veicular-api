package com.herick.ultracarapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NomeValidator implements ConstraintValidator<Nome, String> {

  @Override
  public boolean isValid(String valor, ConstraintValidatorContext context) { // Lógica que uso para validar o atributo "nome" em clienteDTO
    if (valor == null || valor.trim().isEmpty()) {
      return false;
    }

    String regex = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$"; // regex de validação

    return valor.matches(regex);
  }
}
