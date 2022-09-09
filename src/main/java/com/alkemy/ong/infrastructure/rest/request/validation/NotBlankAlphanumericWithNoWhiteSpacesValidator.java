package com.alkemy.ong.infrastructure.rest.request.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class NotBlankAlphanumericWithNoWhiteSpacesValidator implements
    ConstraintValidator<NotBlankAlphanumericWithNoWhiteSpaces, String> {

  private static final String REGEXP_NOT_BLANK_ALPHANUMERIC_WITHOUT_SPACES = "^[\\w.]+$";

  @Override
  public void initialize(NotBlankAlphanumericWithNoWhiteSpaces constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String body, ConstraintValidatorContext constraintValidatorContext) {
    Pattern pattern = Pattern.compile(REGEXP_NOT_BLANK_ALPHANUMERIC_WITHOUT_SPACES);
    return StringUtils.hasText(body) && pattern.matcher(body).matches();
  }

}
