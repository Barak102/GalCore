package com.gal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class Tag {
    int id;
    @Size(min=2)
    String name;
    int orderId;
}
