package com.hostel.management.entity.enums;

/**
 * Room availability states.
 */
public enum RoomStatus {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    MAINTENANCE("Maintenance"),
    RESERVED("Reserved");

    private final String displayName;

    RoomStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
