package com.gal.entities.customValidators;

import com.gal.entities.customValidators.impl.EmailValidatorImpl;
import com.gal.entities.customValidators.impl.PasswordValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidatorImpl.class)
@Documented
public @interface PasswordValidator {

    boolean forceSpecialChar() default false;
    boolean forceCapitalLetter() default false;
    boolean forceNumber() default false;
    int minLength() default 4;
    int maxLength() default 10;


    String message() default "{com.mycompany.constraints.nullornotempty}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
