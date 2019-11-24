package com.gal.entities.customValidators.impl;

import com.gal.entities.customValidators.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidatorImpl implements ConstraintValidator<EmailValidator, Object> {


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    private boolean isRequired;

    @Override
    public void initialize(EmailValidator constraintAnnotation) {
        isRequired = constraintAnnotation.isRequired();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

        String emailStr = (String) object;
        if (!isRequired) {
            if (emailStr.length() == 0) {
                return true;
            }
        }



        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();

        /*
         * final Class<?> fieldClass = object.getClass();
         * if (fieldClass.isArray()) {
         * return Array.getLength(object) > 0;
         * }
         * 
         * if (Iterable.class.isAssignableFrom(fieldClass)) {
         * return ((Iterable) object).iterator().hasNext();
         * }
         * 
         * if (Map.class.isAssignableFrom(fieldClass)) {
         * return !((Map) object).isEmpty();
         * }
         */
    }
}
