package com.gal.domain.validator;

import com.gal.domain.common.exception.ValidationFaultException;

public interface ValidatorBase<T> {
    void validRequest(T obj) throws ValidationFaultException;
    void validRequest(int id) throws ValidationFaultException;
}
