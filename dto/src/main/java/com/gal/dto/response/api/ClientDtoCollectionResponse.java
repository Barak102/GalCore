package com.gal.dto.response.api;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDtoCollectionResponse {
    List<ClientDtoResponse> clients;
}
