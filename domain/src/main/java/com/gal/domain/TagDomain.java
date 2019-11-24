package com.gal.domain;

import java.io.IOException;

import com.gal.domain.common.base.InternalDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.TagDomainRequest;
import com.gal.domain.response.TagDomainCollectionResponse;
import com.gal.domain.response.TagDomainResponse;

public interface TagDomain extends InternalDomain<TagDomainRequest,TagDomainResponse,TagDomainCollectionResponse> {
    TagDomainCollectionResponse getByOrderId(int orderId) throws IOException, ValidationFaultException;
    void addTagNamesToOrder(int orderId, String[] tags) throws IOException, ValidationFaultException;

    void deleteTagNamesFromOrder(int orderId, String[] names) throws IOException, ValidationFaultException;
}
