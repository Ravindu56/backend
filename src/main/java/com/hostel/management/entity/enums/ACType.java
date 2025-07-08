package com.hostel.management.entity.enums;

/**
 * Air conditioning types for rooms.
 */
public enum ACType {
    AC("AC"),
    NON_AC("Non-AC");

    private final String displayName;

    ACType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
