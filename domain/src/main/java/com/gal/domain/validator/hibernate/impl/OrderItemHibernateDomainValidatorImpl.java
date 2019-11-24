package com.gal.domain.validator.hibernate.impl;

import org.springframework.stereotype.Component;

import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.OrderItem;

@Component("OrderItemHibernateDomainValidatorImpl")
public class OrderItemHibernateDomainValidatorImpl implements HibernateDomainValidator<OrderItem> {
}
