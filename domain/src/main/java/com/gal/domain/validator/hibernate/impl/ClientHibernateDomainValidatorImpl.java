package com.gal.domain.validator.hibernate.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.GalClient;

@Component("ClientHibernateDomainValidatorImpl")
public class ClientHibernateDomainValidatorImpl implements HibernateDomainValidator<GalClient> {

    public Validator validator;

    public ClientHibernateDomainValidatorImpl() {
        validator = getValidator();
    }


    @Override
    public void validRequest(GalClient obj) throws ValidationFaultException {
        Set<ConstraintViolation<GalClient>> constraintViolations = getValidator().validate(obj);
        if (constraintViolations.size() > 0) {
            throw new ValidationFaultException(buildValidatorMessage(constraintViolations));
        }
    }


}
