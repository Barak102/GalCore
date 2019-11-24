package com.gal.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    PROPOSED(1),
    APPROVED(2),
    CANCELED(3);

        OrderStatus(final int status) {
            this.status = status;
        }

    public int getStatus() {
        return this.status;
    }

    private int status;

    private static Map<Integer, OrderStatus> map = new HashMap<Integer, OrderStatus>();

    static {
        for (OrderStatus statusVal : OrderStatus.values()) {
            map.put(statusVal.status, statusVal);
        }
    }

    public static OrderStatus valueOf(int orderStatusId) {
        return map.get(orderStatusId);
    }

}
