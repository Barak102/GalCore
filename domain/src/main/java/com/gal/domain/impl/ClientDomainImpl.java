package com.gal.domain.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gal.domain.ClientDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.ClientDomainRequest;
import com.gal.domain.response.ClientDomainCollectionResponse;
import com.gal.domain.response.ClientDomainResponse;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.GalClient;
import gap.app.contracts.ClientStorageManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


@Service("ClientDomainImpl")
public class ClientDomainImpl implements ClientDomain {

    @Autowired
    private ClientStorageManager storage;

    @Autowired
    @Qualifier("ClientHibernateDomainValidatorImpl")
    private HibernateDomainValidator<GalClient> validator;

    @Override
    public ClientDomainResponse getById(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        GalClient client = storage.getById(id);
        ClientDomainResponse response = null;
        if (client != null) {
            response = new ClientDomainResponse();
            response.setClient(client);
        }
        return response;
    }

    @Override
    public ClientDomainCollectionResponse getList(int[] ids) {
        ClientDomainCollectionResponse response = new ClientDomainCollectionResponse();
        response.setClients(new ArrayList<>(storage.getByIds(ids)));
        return response;
    }

    @Override
    public int add(ClientDomainRequest request) throws IOException, ValidationFaultException {
        GalClient client = request.getClient();
        validator.validRequest(client);
        return storage.save(client);
    }

    @Override
    public void update(ClientDomainRequest request) throws IOException, ValidationFaultException {
        GalClient client = request.getClient();
        validator.validRequest(client);
        storage.save(request.getClient());
    }

    @Override
    public void delete(int id) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public ClientDomainCollectionResponse getAll() throws IOException {
        ClientDomainCollectionResponse response = new ClientDomainCollectionResponse();
        List<GalClient> clients = new ArrayList<>(storage.getAll());
        response.setClients(clients);
        return response;
    }
}
