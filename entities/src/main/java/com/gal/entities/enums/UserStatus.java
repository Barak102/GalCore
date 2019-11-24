package com.gal.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {

    ACTIVE(1),
    NOT_ACTIVE(2),
    DELETED(3);

    private int statusId;

    UserStatus(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return this.statusId;
    }


    private static Map<Integer, UserStatus> map = new HashMap<>();


    static {
        for (UserStatus statusVal : UserStatus.values()) {
            map.put(statusVal.statusId, statusVal);
        }
    }

    public static UserStatus valueOf(int statusId) {
        return map.get(statusId);
    }
}
