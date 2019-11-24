package com.gal.domain.request;

import com.gal.domain.common.base.DomainRequest;
import com.gal.entities.GalClient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ClientDomainRequest extends DomainRequest {
    GalClient client;
}
