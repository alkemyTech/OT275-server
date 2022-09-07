package com.alkemy.ong.infrastructure.rest.request.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class NotBlankAlphanumericValidator implements
    ConstraintValidator<NotBlankAlphanumeric, String> {

  private static final String REGEXP_NOT_BLANK_ALPHANUMERIC = "^[A-Za-z0-9]+$";

  @Override
  public void initialize(NotBlankAlphanumeric constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String body, ConstraintValidatorContext constraintValidatorContext) {
    Pattern pattern = Pattern.compile(REGEXP_NOT_BLANK_ALPHANUMERIC);
    return StringUtils.hasText(body) && pattern.matcher(body).matches();
  }
}
