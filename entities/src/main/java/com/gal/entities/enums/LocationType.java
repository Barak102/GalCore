package com.gal.entities.enums;

import java.util.HashMap;
import java.util.Map;

public enum LocationType {
    CLIENT_HOUSE(1),
    BUSINESS(2),
    OTHER(3);

    LocationType(final int locationType) {
        this.locationType = locationType;
    }

    public int getLocationTypeId() {
        return this.locationType;
    }

    private int locationType;

    private static Map<Integer, LocationType> map = new HashMap<Integer, LocationType>();

    static {
        for (LocationType locationVal : LocationType.values()) {
            map.put(locationVal.locationType, locationVal);
        }
    }

    public static LocationType valueOf(int locationTypeId) {
        return map.get(locationTypeId);
    }

}
