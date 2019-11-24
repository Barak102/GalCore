package com.gal.domain.validator.hibernate.impl;

import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.User;
import org.springframework.stereotype.Component;

@Component("UserHibernateDomainValidatorImpl")
public class UserHibernateDomainValidatorImpl implements HibernateDomainValidator<User> {
}
