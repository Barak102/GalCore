package com.gal.domain.common.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationFaultException extends Exception {

    private Set<ConstraintViolation<Object>> constraintViolations;


    public ValidationFaultException(String message) {
        super(message);
    }
}
