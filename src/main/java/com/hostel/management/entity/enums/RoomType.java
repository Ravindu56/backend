package com.hostel.management.entity.enums;

/**
 * Room capacity classifications.
 */
public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double"),
    TRIPLE("Triple");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
