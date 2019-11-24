package com.gal.domain.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gal.domain.TagDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.TagDomainRequest;
import com.gal.domain.response.TagDomainCollectionResponse;
import com.gal.domain.response.TagDomainResponse;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.Tag;
import gap.app.contracts.TagStorageManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service("TagDomainImpl")
public class TagDomainImpl implements TagDomain {


    @Autowired
    TagStorageManager storage;

    @Autowired
    @Qualifier("TagHibernateDomainValidatorImpl")
    HibernateDomainValidator<Tag> validator;

    @Override
    public TagDomainResponse getById(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        TagDomainResponse response = new TagDomainResponse();
        response.setTag(storage.getById(id));
        return response;
    }

    @Override
    public TagDomainCollectionResponse getList(int[] ids) throws IOException {
        TagDomainCollectionResponse response = new TagDomainCollectionResponse();
        response.setTags(new ArrayList<>(storage.getByIds(ids)));
        return response;
    }

    @Override
    public int add(TagDomainRequest request) throws IOException, ValidationFaultException {
        Tag tag = request.getTag();
        validator.validRequest(tag);
        return storage.save(request.getTag());
    }

    @Override
    public void update(TagDomainRequest request) throws IOException, ValidationFaultException {
        Tag tag = request.getTag();
        validator.validRequest(tag);
        storage.save(tag);
    }

    @Override
    public void delete(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        storage.delete(id);
    }

    @Override
    public TagDomainCollectionResponse getAll() throws IOException {
        TagDomainCollectionResponse response = new TagDomainCollectionResponse();
        response.setTags(new ArrayList<>(storage.getAll()));
        return response;
    }

    @Override
    public TagDomainCollectionResponse getByOrderId(int orderId) throws IOException, ValidationFaultException {
        validator.validRequest(orderId);
        TagDomainCollectionResponse response = new TagDomainCollectionResponse();
        response.setTags(new ArrayList<>(storage.getByOrderId(orderId)));
        return  response;
    }

    @Override
    public void addTagNamesToOrder(int orderId, String[] tags) throws IOException, ValidationFaultException {
        validator.validRequest(orderId);
        storage.saveTagNamesToOrder(orderId, tags);
    }

    @Override
    public void deleteTagNamesFromOrder(int orderId, String[] tags) throws IOException, ValidationFaultException {
        validator.validRequest(orderId);
        storage.deleteTagNamesFromOrder(orderId, tags);
    }
}
