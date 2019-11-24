package com.gal.entities.customValidators;

import com.gal.entities.customValidators.impl.EmailValidatorImpl;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidatorImpl.class)
@Documented
public @interface EmailValidator {

    boolean isRequired() default false;

    String message() default "{com.mycompany.constraints.nullornotempty}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
