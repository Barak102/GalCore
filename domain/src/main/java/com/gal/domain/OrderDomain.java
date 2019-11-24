package com.gal.domain;

import com.gal.domain.common.base.InternalDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.OrderDomainRequest;
import com.gal.domain.response.OrderDomainCollectionResponse;
import com.gal.domain.response.OrderDomainResponse;

import java.io.IOException;

public interface OrderDomain extends InternalDomain<OrderDomainRequest,OrderDomainResponse,OrderDomainCollectionResponse> {
    void cancelOrder(int orderId) throws IOException, ValidationFaultException;
    void approveOrder(int orderId) throws IOException, ValidationFaultException;
    OrderDomainCollectionResponse getByClientId(int clientId) throws IOException, ValidationFaultException;
    OrderDomainCollectionResponse getByTagsName(String[] tags);
    OrderDomainCollectionResponse getByTagIds(int[] tagsIds);
}
