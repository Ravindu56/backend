package com.hostel.management.entity.enums;

/**
 * Issue classification types for complaints.
 */
public enum ComplaintCategory {
    MAINTENANCE("Maintenance"),
    CLEANLINESS("Cleanliness"),
    ELECTRICAL("Electrical"),
    PLUMBING("Plumbing"),
    FOOD("Food"),
    OTHER("Other");

    private final String displayName;

    ComplaintCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
