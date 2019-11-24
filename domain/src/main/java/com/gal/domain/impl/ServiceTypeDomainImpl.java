package com.gal.domain.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gal.domain.ServiceTypeDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.ServiceTypeDomainRequest;
import com.gal.domain.response.ServiceTypeDomainCollectionResponse;
import com.gal.domain.response.ServiceTypeDomainResponse;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.ServiceType;
import gap.app.contracts.ServiceTypeStorageManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service("ServiceTypeDomainImpl")
public class ServiceTypeDomainImpl implements ServiceTypeDomain {

    @Autowired
    ServiceTypeStorageManager storage;

    @Autowired
    @Qualifier("ServiceTypeHibernateDomainValidatorImpl")
    HibernateDomainValidator<ServiceType> validator;


    @Override
    public ServiceTypeDomainResponse getById(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        ServiceTypeDomainResponse response = new ServiceTypeDomainResponse();
        response.setServiceType(storage.getById(id));
        return response;
    }

    @Override
    public ServiceTypeDomainCollectionResponse getList(int[] ids) throws IOException {
        ServiceTypeDomainCollectionResponse response = new ServiceTypeDomainCollectionResponse();
        response.setServiceTypes(new ArrayList<>(storage.getByIds(ids)));
        return response;
    }

    @Override
    public int add(ServiceTypeDomainRequest request) throws IOException, ValidationFaultException {
        ServiceType serviceType = request.getServiceType();
        validator.validRequest(serviceType);
        return storage.save(serviceType);
    }

    @Override
    public void update(ServiceTypeDomainRequest request) throws IOException, ValidationFaultException {
        ServiceType serviceType = request.getServiceType();
        validator.validRequest(serviceType);
        storage.save(serviceType);

    }

    @Override
    public void delete(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        storage.delete(id);
    }

    @Override
    public ServiceTypeDomainCollectionResponse getAll() throws IOException {
        ServiceTypeDomainCollectionResponse response = new ServiceTypeDomainCollectionResponse();
        response.setServiceTypes(new ArrayList<>(storage.getAll()));
        return response;
    }
}
