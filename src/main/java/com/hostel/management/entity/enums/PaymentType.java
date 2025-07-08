package com.hostel.management.entity.enums;

/**
 * Payment categories.
 */
public enum PaymentType {
    MONTHLY_RENT("Monthly Rent"),
    SECURITY_DEPOSIT("Security Deposit"),
    MESS_FEE("Mess Fee"),
    PENALTY("Penalty"),
    OTHER("Other");

    private final String displayName;

    PaymentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
