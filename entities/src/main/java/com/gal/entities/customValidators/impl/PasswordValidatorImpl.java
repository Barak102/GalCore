package com.gal.entities.customValidators.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gal.entities.customValidators.PasswordValidator;

public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator, Object> {
    private static String pattern = null;


    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        StringBuilder patternBuilder = new StringBuilder("((?=.*[a-z])");

        if (constraintAnnotation.forceSpecialChar()) {
            patternBuilder.append("(?=.*[@#$%])");
        }

        if (constraintAnnotation.forceCapitalLetter()) {
            patternBuilder.append("(?=.*[A-Z])");
        }

        if (constraintAnnotation.forceNumber()) {
            patternBuilder.append("(?=.*d)");
        }

        patternBuilder.append(".{" + constraintAnnotation.minLength() + "," + constraintAnnotation.maxLength() + "})");
        pattern = patternBuilder.toString();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher((String) value);
        return m.matches();
    }
}
