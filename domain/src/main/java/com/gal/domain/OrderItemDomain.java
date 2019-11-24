package com.gal.domain;

import com.gal.domain.common.base.InternalDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.OrderItemDomainRequest;
import com.gal.domain.response.OrderItemDomainCollectionResponse;
import com.gal.domain.response.OrderItemDomainResponse;

import java.io.IOException;

public interface OrderItemDomain extends InternalDomain<OrderItemDomainRequest,OrderItemDomainResponse,OrderItemDomainCollectionResponse> {
    OrderItemDomainCollectionResponse getByOrderId(int id) throws IOException, ValidationFaultException;
}
