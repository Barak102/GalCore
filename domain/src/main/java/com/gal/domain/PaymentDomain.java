package com.gal.domain;

import com.gal.domain.common.base.InternalDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.PaymentDomainRequest;
import com.gal.domain.response.PaymentDomainCollectionResponse;
import com.gal.domain.response.PaymentDomainResponse;

import java.io.IOException;

public interface PaymentDomain extends InternalDomain<PaymentDomainRequest,PaymentDomainResponse,PaymentDomainCollectionResponse> {
    PaymentDomainCollectionResponse getByOrderId(int orderId) throws IOException, ValidationFaultException;
}
