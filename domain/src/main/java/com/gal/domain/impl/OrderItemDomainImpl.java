package com.gal.domain.impl;

import java.io.IOException;
import java.util.ArrayList;

import com.gal.domain.common.exception.ValidationFaultException;
import gap.app.contracts.OrderItemStorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gal.domain.OrderItemDomain;
import com.gal.domain.request.OrderItemDomainRequest;
import com.gal.domain.response.OrderItemDomainCollectionResponse;
import com.gal.domain.response.OrderItemDomainResponse;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.OrderItem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service("OrderItemDomainImpl")
public class OrderItemDomainImpl implements OrderItemDomain {

    @Autowired
    OrderItemStorageManager storage;

    @Autowired
    @Qualifier("OrderItemHibernateDomainValidatorImpl")
    HibernateDomainValidator<OrderItem> validator;

    @Override
    public OrderItemDomainResponse getById(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        OrderItemDomainResponse response = new OrderItemDomainResponse();
        response.setOrderItem(storage.getById(id));
        return  response;
    }

    @Override
    public OrderItemDomainCollectionResponse getList(int[] ids) throws IOException {
        OrderItemDomainCollectionResponse response = new OrderItemDomainCollectionResponse();
        response.setOrderItems(new ArrayList<>(storage.getByIds(ids)));
        return response;
    }

    @Override
    public int add(OrderItemDomainRequest request) throws IOException, ValidationFaultException {
        OrderItem orderItem = request.getOrderItem();
        validator.validRequest(orderItem);
        return storage.save(orderItem);
    }

    @Override
    public void update(OrderItemDomainRequest request) throws IOException, ValidationFaultException {
        OrderItem orderItem = request.getOrderItem();
        validator.validRequest(orderItem);
        storage.save(orderItem);
    }

    @Override
    public void delete(int id) throws IOException, ValidationFaultException {
        //delete only if not attatched to order & the order getStatus is proposed
        validator.validRequest(id);
        storage.delete(id);
    }

    @Override
    public OrderItemDomainCollectionResponse getAll() throws IOException {
        OrderItemDomainCollectionResponse response = new OrderItemDomainCollectionResponse();
        response.setOrderItems(new ArrayList<>(storage.getAll()));
        return  response;
    }

    @Override
    public OrderItemDomainCollectionResponse getByOrderId(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        OrderItemDomainCollectionResponse response = new OrderItemDomainCollectionResponse();
        response.setOrderItems(new ArrayList<>(storage.getByOrderId(id)));
        return response;
    }
}
