package com.herick.ultracarapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NomeValidator implements ConstraintValidator<Nome, String> {

  @Override
  public boolean isValid(String valor, ConstraintValidatorContext context) {
    if (valor == null || valor.trim().isEmpty()) {
      return false;
    }

    String regex = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$";

    return valor.matches(regex);
  }
}
