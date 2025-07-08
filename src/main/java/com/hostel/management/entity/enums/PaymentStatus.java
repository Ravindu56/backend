package com.hostel.management.entity.enums;

/**
 * Payment processing states.
 */
public enum PaymentStatus {
    PENDING("Pending"),
    PAID("Paid"),
    OVERDUE("Overdue"),
    CANCELLED("Cancelled");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
