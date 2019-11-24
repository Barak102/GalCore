package com.gal.domain.validator.hibernate.impl;

import org.springframework.stereotype.Component;

import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.Tag;

@Component("TagHibernateDomainValidatorImpl")
public class TagHibernateDomainValidatorImpl implements HibernateDomainValidator<Tag> {
}
