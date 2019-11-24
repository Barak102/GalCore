package com.gal.domain;

import com.gal.domain.common.base.InternalDomain;
import com.gal.domain.request.ClientDomainRequest;
import com.gal.domain.response.ClientDomainCollectionResponse;
import com.gal.domain.response.ClientDomainResponse;

public interface ClientDomain extends InternalDomain<ClientDomainRequest,ClientDomainResponse,ClientDomainCollectionResponse> {
}
