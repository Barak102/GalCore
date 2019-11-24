package com.gal.db.repository;

import com.gal.entities.Payment;

import java.util.Collection;

public interface PaymentRepository extends Repository<Payment> {
    Collection<Payment> getByOrderId(int orderId);
}
