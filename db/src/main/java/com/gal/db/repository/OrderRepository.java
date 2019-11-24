package com.gal.db.repository;

import com.gal.entities.Order;

import java.util.Collection;

public interface OrderRepository extends Repository<Order> {
    void cancel(int orderId);

    void approve(int orderId);

    Collection<Order> getByClientId(int clientId);

    Collection<Order> getByTagsName(String[] tags);

    Collection<Order> getByTagsIds(int[] tagsIds);
}
