package com.gal.entities;

import java.util.List;

import javax.validation.constraints.Size;

public class OrderGroup {
    int id;
    @Size(min=1)
    int clientId;
    List<Order> orders;
}
