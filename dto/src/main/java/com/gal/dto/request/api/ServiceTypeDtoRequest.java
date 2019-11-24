package com.gal.dto.request.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceTypeDtoRequest {
    int id;
    String name;
    String description;
    double price;
}
