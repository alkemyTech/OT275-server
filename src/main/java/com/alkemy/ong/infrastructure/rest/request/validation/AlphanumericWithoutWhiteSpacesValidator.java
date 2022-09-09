package com.alkemy.ong.infrastructure.rest.request.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class AlphanumericWithoutWhiteSpacesValidator implements
    ConstraintValidator<AlphanumericWithoutWhiteSpaces, String> {

  private static final String REGEXP_ALPHANUMERIC_WITHOUT_WHITE_SPACES = "^[\\w.]+$";

  @Override
  public void initialize(AlphanumericWithoutWhiteSpaces constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String body, ConstraintValidatorContext constraintValidatorContext) {
    Pattern pattern = Pattern.compile(REGEXP_ALPHANUMERIC_WITHOUT_WHITE_SPACES);
    return StringUtils.hasText(body) && pattern.matcher(body).matches();
  }

}
