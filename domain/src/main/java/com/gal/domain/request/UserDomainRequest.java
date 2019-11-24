package com.gal.domain.request;

import com.gal.domain.common.base.DomainRequest;
import com.gal.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDomainRequest extends DomainRequest {
    User user;
}
