package com.gal.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {
    ADMIN(1);

    private int roleId;

    UserRole(int roleId) {
        this.roleId = roleId;
    }

    public int gerRoleId() {
        return this.roleId;
    }


    private static Map<Integer, UserRole> map = new HashMap<>();


    static {
        for (UserRole roleVal : UserRole.values()) {
            map.put(roleVal.roleId, roleVal);
        }
    }

    public static UserRole valueOf(int userRoleId) {
        return map.get(userRoleId);
    }

}
