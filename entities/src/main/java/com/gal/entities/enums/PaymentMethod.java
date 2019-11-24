package com.gal.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentMethod {
    CREDIT_CARD(1),
    CASH(2),
    BANK_TRANSFER(3),
    GIFT(4); // for free

    private int method;

    PaymentMethod(int payment) {
        this.method = payment;
    }

    public int getMethod() {
        return this.method;
    }


    private static Map<Integer, PaymentMethod> map = new HashMap<Integer, PaymentMethod>();


    static {
        for (PaymentMethod methodVal : PaymentMethod.values()) {
            map.put(methodVal.method, methodVal);
        }
    }

    public static PaymentMethod valueOf(int paymentMethodId) {
        return map.get(paymentMethodId);
    }


}
