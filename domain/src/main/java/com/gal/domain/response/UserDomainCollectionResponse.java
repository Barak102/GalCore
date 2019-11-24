package com.gal.domain.response;

import com.gal.domain.common.base.DomainCollectionResponse;
import com.gal.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDomainCollectionResponse extends DomainCollectionResponse {
    List<User> users;
}
