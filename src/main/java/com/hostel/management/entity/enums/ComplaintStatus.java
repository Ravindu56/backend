package com.hostel.management.entity.enums;

/**
 * Complaint lifecycle states.
 */
public enum ComplaintStatus {
    PENDING("Pending"),
    RESOLVED("Resolved"),
    IN_PROGRESS("In Progress"),
    CLOSED("Closed"),
    REJECTED("Rejected");

    private final String displayName;

    ComplaintStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
