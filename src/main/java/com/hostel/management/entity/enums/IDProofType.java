package com.hostel.management.entity.enums;

/**
 * ID proof types for visitors.
 */
public enum IDProofType {
    AADHAAR("Aadhaar Card"),
    PASSPORT("Passport"),
    DRIVING_LICENSE("Driving License"),
    VOTER_ID("Voter ID"),
    OTHER("Other");

    private final String displayName;

    IDProofType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
