package com.gal.dto.response.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceTypeDtoResponse {
    int id;
    String name;
    String description;
    double price;
}
