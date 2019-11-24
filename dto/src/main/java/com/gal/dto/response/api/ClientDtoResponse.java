package com.gal.dto.response.api;


import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDtoResponse
{
    int id;
    String firstName;
    String lastName;
    String city;
    String address;
    String email;
    String phone;
    List<OrderDtoResponse> orders;
}
