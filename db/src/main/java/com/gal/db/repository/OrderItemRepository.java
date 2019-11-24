package com.gal.db.repository;

import com.gal.entities.OrderItem;

import java.util.Collection;

public interface OrderItemRepository extends Repository<OrderItem> {
    Collection<OrderItem> getByOrderId();
    void saveOrderItems(Collection<OrderItem> orderItems);
}
