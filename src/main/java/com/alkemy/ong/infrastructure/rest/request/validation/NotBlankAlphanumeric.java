package com.alkemy.ong.infrastructure.rest.request.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotBlankAlphanumericValidator.class)
public @interface NotBlankAlphanumeric {

  String message()
     default "{com.alkemy.ong.infrastructure.rest.request.validation.NotBlankAlphanumeric.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
