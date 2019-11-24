package com.gal.domain.response;

import com.gal.domain.common.base.DomainResponse;
import com.gal.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDomainResponse extends DomainResponse {
    User user;
}
