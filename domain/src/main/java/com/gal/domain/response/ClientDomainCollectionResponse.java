package com.gal.domain.response;

import com.gal.domain.common.base.DomainCollectionResponse;
import com.gal.entities.GalClient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDomainCollectionResponse extends DomainCollectionResponse {
    List<GalClient> clients;
}
