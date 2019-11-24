package com.gal.domain;

import com.gal.domain.common.base.InternalDomain;
import com.gal.domain.request.UserDomainRequest;
import com.gal.domain.response.UserDomainCollectionResponse;
import com.gal.domain.response.UserDomainResponse;

public interface UserDomain extends InternalDomain<UserDomainRequest,UserDomainResponse,UserDomainCollectionResponse> {
    UserDomainResponse getByEmail(String email);
}
