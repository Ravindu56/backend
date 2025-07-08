package com.hostel.management.entity.enums;

/**
 * Payment processing methods.
 */
public enum PaymentMethod {
    CASH("Cash"),
    ONLINE("Online"),
    CHEQUE("Cheque"),
    CARD("Card");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
