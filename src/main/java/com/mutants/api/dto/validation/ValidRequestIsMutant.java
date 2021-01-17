package com.mutants.api.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RequestIsMutantValidator.class})
public @interface ValidRequestIsMutant {

  String message() default "Invalid request";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
