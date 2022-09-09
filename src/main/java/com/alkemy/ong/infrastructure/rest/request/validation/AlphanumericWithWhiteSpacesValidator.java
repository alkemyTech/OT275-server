package com.alkemy.ong.infrastructure.rest.request.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class AlphanumericWithWhiteSpacesValidator implements
    ConstraintValidator<AlphanumericWithWhiteSpaces, String> {

  private static final String REGEXP = "^[\\w\\s]+$";

  @Override
  public void initialize(AlphanumericWithWhiteSpaces constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String body, ConstraintValidatorContext constraintValidatorContext) {
    Pattern pattern = Pattern.compile(REGEXP);
    return StringUtils.hasText(body) && pattern.matcher(body).matches();
  }
}
