package com.alkemy.ong.infrastructure.rest.request.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

  private static final String REGEXP_NAME = "^\\p{L}+[\\p{L}\\s]*$";

  @Override
  public void initialize(Name constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
    Pattern pattern = Pattern.compile(REGEXP_NAME);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }
}
