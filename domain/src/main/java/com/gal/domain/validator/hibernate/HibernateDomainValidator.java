package com.gal.domain.validator.hibernate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.validator.ValidatorBase;

public interface HibernateDomainValidator<T> extends ValidatorBase<T> {

    @Override
    default public void validRequest(T obj) throws ValidationFaultException {
        Set<ConstraintViolation<T>> constraintViolations = getValidator().validate(obj);
            if (!constraintViolations.isEmpty()) {
                throw new ValidationFaultException(buildValidatorMessage(constraintViolations));
            }
    }

    @Override
    default public void validRequest(int id) throws ValidationFaultException {
        if (id <= 0) {
            throw new ValidationFaultException("Msg");
        }
    }


    default public Validator getValidator() {
        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        return validator;
    }

    default public String buildValidatorMessage(Set<ConstraintViolation<T>> constraintViolations) {
        String message = "";
        int ind = 0;
        if (constraintViolations != null) {
            for (ConstraintViolation c : constraintViolations) {
                message += c.getMessage() + " ,";
                if (ind + 1 < constraintViolations.size()) {
                    message += " ,";
                }
            }
        }
        return message;
    }
}
