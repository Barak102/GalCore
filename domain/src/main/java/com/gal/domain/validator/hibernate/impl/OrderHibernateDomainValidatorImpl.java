package com.gal.domain.validator.hibernate.impl;

import org.springframework.stereotype.Component;

import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.Order;

@Component("OrderHibernateDomainValidatorImpl")
public class OrderHibernateDomainValidatorImpl implements HibernateDomainValidator<Order> {
}
