package com.gal.domain.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gal.domain.OrderDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.OrderDomainRequest;
import com.gal.domain.response.OrderDomainCollectionResponse;
import com.gal.domain.response.OrderDomainResponse;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.Order;
import com.gal.entities.enums.OrderStatus;
import gap.app.contracts.OrderStorageManager;

@Service("OrderDomainImpl")
public class OrderDomainImpl implements OrderDomain {


    @Autowired
    OrderStorageManager storage;

    @Autowired
    @Qualifier("OrderHibernateDomainValidatorImpl")
    HibernateDomainValidator<Order> validator;


    @Override
    public OrderDomainResponse getById(int id) throws ValidationFaultException, IOException {
        validator.validRequest(id);
        OrderDomainResponse response = new OrderDomainResponse();
        Order result = storage.getById(id);
        if (result == null) {
            throw new IOException("Order not found");
        }
        response.setOrder(storage.getById(id));
        return response;
    }

    @Override
    public OrderDomainCollectionResponse getList(int[] ids) throws IOException {
        OrderDomainCollectionResponse response = new OrderDomainCollectionResponse();
        response.setOrders(new ArrayList<>(storage.getByIds(ids)));
        return response;
    }

    @Override
    public int add(OrderDomainRequest request) throws IOException, ValidationFaultException {
        Order order = request.getOrder();
        validator.validRequest(order);
        order.setStatus(OrderStatus.PROPOSED);
        return storage.save(order);
    }

    @Override
    public void update(OrderDomainRequest request) throws IOException, ValidationFaultException {
        validator.validRequest(request.getOrder());
        storage.save(request.getOrder());
    }

    @Override
    public void delete(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        storage.delete(id);
    }

    @Override
    public OrderDomainCollectionResponse getAll() throws IOException {
        OrderDomainCollectionResponse respone = new OrderDomainCollectionResponse();
        respone.setOrders(new ArrayList<>(storage.getAll()));
        return respone;
    }

    @Override
    public void cancelOrder(int orderId) throws IOException, ValidationFaultException {
        validator.validRequest(orderId);
        storage.cancel(orderId);
    }

    @Override
    public void approveOrder(int orderId) throws IOException, ValidationFaultException {
        validator.validRequest(orderId);
        storage.approve(orderId);
    }

    @Override
    public OrderDomainCollectionResponse getByClientId(int clientId) throws IOException, ValidationFaultException {
        validator.validRequest(clientId);
        OrderDomainCollectionResponse response = new OrderDomainCollectionResponse();
        response.setOrders(new ArrayList<>(storage.getByClientId(clientId)));
        return response;
    }

    @Override
    public OrderDomainCollectionResponse getByTagsName(String[] tags) {
        OrderDomainCollectionResponse response = new OrderDomainCollectionResponse();
        response.setOrders(new ArrayList<Order>(storage.getByTagsName(tags)));
        return response;
    }

    @Override
    public OrderDomainCollectionResponse getByTagIds(int[] tagsIds) {
        OrderDomainCollectionResponse response = new OrderDomainCollectionResponse();
        response.setOrders(new ArrayList<Order>(storage.getByTagsIds(tagsIds)));
        return response;
    }

}
