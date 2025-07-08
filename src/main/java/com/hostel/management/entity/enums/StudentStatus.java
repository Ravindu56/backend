package com.hostel.management.entity.enums;

/**
 * Student lifecycle states.
 */
public enum StudentStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    GRADUATED("Graduated"),
    TRANSFERRED("Transferred");

    private final String displayName;

    StudentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
