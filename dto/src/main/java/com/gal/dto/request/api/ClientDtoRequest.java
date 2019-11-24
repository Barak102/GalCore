package com.gal.dto.request.api;

import com.gal.entities.GalClient;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class ClientDtoRequest {
    int id;
    String firstName;
    String lastName;
    String city;
    String address;
    String email;
    String phone;
}
