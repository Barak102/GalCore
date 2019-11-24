package com.gal.domain.validator.hibernate.impl;

import org.springframework.stereotype.Component;

import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.Payment;

@Component("PaymentHibernateDomainValidatorImpl")
public class PaymentHibernateDomainValidatorImpl implements HibernateDomainValidator<Payment> {
}
