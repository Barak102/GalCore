package com.gal.db.repository;

import java.util.Collection;

import com.gal.entities.Tag;

public interface TagRepository extends Repository<Tag> {
    Collection<Tag> getByOrderId(int orderId);
    void saveTagNamesToOrder(int orderId, String[] tags);

    void deleteTagNamesFromOrder(int orderId, String[] tags);
}
