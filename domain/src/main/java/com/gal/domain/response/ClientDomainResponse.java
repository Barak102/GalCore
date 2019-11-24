package com.gal.domain.response;

import com.gal.domain.common.base.DomainResponse;
import com.gal.entities.GalClient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDomainResponse extends DomainResponse {
    GalClient client;
}
