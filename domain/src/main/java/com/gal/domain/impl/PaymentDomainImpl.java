package com.gal.domain.impl;

import java.io.IOException;
import java.util.ArrayList;

import com.gal.domain.common.exception.ValidationFaultException;
import gap.app.contracts.PaymentStorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gal.domain.PaymentDomain;
import com.gal.domain.request.PaymentDomainRequest;
import com.gal.domain.response.PaymentDomainCollectionResponse;
import com.gal.domain.response.PaymentDomainResponse;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.Payment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service("PaymentDomainImpl")
public class PaymentDomainImpl implements PaymentDomain {

    @Autowired
    PaymentStorageManager storage;

    @Autowired
    @Qualifier("PaymentHibernateDomainValidatorImpl")
    HibernateDomainValidator<Payment> validator;

    @Override
    public PaymentDomainResponse getById(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        PaymentDomainResponse response = new PaymentDomainResponse();
        response.setPayment(storage.getById(id));
        return response;
    }

    @Override
    public PaymentDomainCollectionResponse getList(int[] ids) throws IOException {
        PaymentDomainCollectionResponse response = new PaymentDomainCollectionResponse();
        response.setPayments(new ArrayList<>(storage.getByIds(ids)));
        return response;
    }

    @Override
    public int add(PaymentDomainRequest request) throws IOException, ValidationFaultException {
        Payment payment = request.getPayment();
        validator.validRequest(payment);
        return storage.save(request.getPayment());
    }

    @Override
    public void update(PaymentDomainRequest request) throws IOException, ValidationFaultException {
        Payment payment = request.getPayment();
        validator.validRequest(payment);
        storage.save(payment);
    }

    @Override
    public void delete(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        storage.delete(id);
    }

    @Override
    public PaymentDomainCollectionResponse getAll() throws IOException {
        PaymentDomainCollectionResponse response = new PaymentDomainCollectionResponse();
        response.setPayments(new ArrayList<>(storage.getAll()));
        return response;
    }

    @Override
    public PaymentDomainCollectionResponse getByOrderId(int orderId) throws IOException, ValidationFaultException {
        validator.validRequest(orderId);
        PaymentDomainCollectionResponse response = new PaymentDomainCollectionResponse();
        response.setPayments(new ArrayList<>(storage.getByOrderId(orderId)));
        return response;
    }
}
