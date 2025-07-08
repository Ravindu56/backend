package com.hostel.management.entity;

import com.hostel.management.entity.enums.Gender;
import com.hostel.management.entity.enums.IDProofType;
import com.hostel.management.entity.enums.VisitorStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "VisitorTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {
    @Id
    @Size(max = 15, message = "Visitor ID must not exceed 15 characters")
    @Column(length = 15)
    private String visitorID;
    @NotBlank(message = "Visitor name is required")
    @Size(max = 100, message = "Visitor name must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String visitorname;
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    @Column(length = 15)
    private String contactnumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull(message = "ID proof type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IDProofType idprooftype;
    @NotBlank(message = "ID proof number is required")
    @Size(max = 50, message = "ID proof number must not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String idproofnumber;
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;
    @Size(max = 50, message = "State must not exceed 50 characters")
    private String state;
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    @Column(length = 6)
    private String pincode;
    @Size(max = 100, message = "Relation must not exceed 100 characters")
    private String relation;
    @Size(max = 500, message = "Purpose must not exceed 500 characters")
    private String purpose;
    @NotNull(message = "Check-in time is required")
    private LocalDateTime checkintime;
    private LocalDateTime checkouttime;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'CHECKED_IN'")
    private VisitorStatus visitorstatus = VisitorStatus.CHECKED_IN;
    @Size(max = 500, message = "Remarks must not exceed 500 characters")
    private String remarks;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdat;
    @UpdateTimestamp
    private LocalDateTime updatedat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomid")
    private Room room;
    public boolean isCheckedIn() {
        return visitorstatus == VisitorStatus.CHECKED_IN;
    }
    public boolean isOverstayed() {
        return visitorstatus == VisitorStatus.OVERSTAYED;
    }
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();
        if (address != null) fullAddress.append(address);
        if (city != null) fullAddress.append(", ").append(city);
        if (state != null) fullAddress.append(", ").append(state);
        if (pincode != null) fullAddress.append(" - ").append(pincode);
        return fullAddress.toString();
    }
    public long getVisitDurationInHours() {
        LocalDateTime endTime = checkouttime != null ? checkouttime : LocalDateTime.now();
        return java.time.temporal.ChronoUnit.HOURS.between(checkintime, endTime);
    }
    public String getVisitSummary() {
        return visitorname + " (" + relation + ") - " + visitorstatus.getDisplayName();
    }
}
