package com.hostel.management.entity.enums;

/**
 * Visitor check-in states.
 */
public enum VisitorStatus {
    CHECKED_IN("Checked In"),
    CHECKED_OUT("Checked Out"),
    OVERSTAYED("Overstayed");

    private final String displayName;

    VisitorStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
