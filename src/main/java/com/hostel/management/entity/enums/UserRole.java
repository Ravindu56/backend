package com.hostel.management.entity.enums;

/**
 * User roles for authentication and authorization.
 */
public enum UserRole {
    STUDENT("Student"),
    WARDEN("Warden"),
    ADMIN("Admin");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
