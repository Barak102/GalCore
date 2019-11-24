package com.gal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ServiceType {
    int id;
    @Size(min=2)
    String name;
    String description;
    @Min(1)
    double price;
}
