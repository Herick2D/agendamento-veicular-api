package com.herick.agendamentoveicularapi.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NomeValidator.class)
public @interface Nome { // Anotação criada como material de estudo e validação do campo nome
  String message() default "Nome inválido";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
