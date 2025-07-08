package com.hostel.management.entity;

import com.hostel.management.entity.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "WardenTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warden {
    @Id
    @Size(max = 10, message = "Warden ID must not exceed 10 characters")
    @Column(length = 10)
    private String wardenID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", unique = true)
    private UserAuth userAuth;
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String firstname;
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String lastname;
    @Formula("CONCAT(firstname, ' ', lastname)")
    private String fullname;
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Column(unique = true, length = 100)
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    @Column(length = 15)
    private String mobilenumber;
    private LocalDate dateofbirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;
    @Size(max = 50, message = "State must not exceed 50 characters")
    private String state;
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    @Column(length = 6)
    private String pincode;
    private LocalTime shiftstarttime;
    private LocalTime shiftendtime;
    @Pattern(regexp = "^[0-9]{10}$", message = "Emergency contact must be 10 digits")
    @Column(length = 15)
    private String emergencycontact;
    @Size(max = 100, message = "Emergency contact name must not exceed 100 characters")
    private String emergencycontactname;
    @Size(max = 50, message = "Emergency contact relation must not exceed 50 characters")
    private String emergencycontactrelation;
    private LocalDate joiningdate;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdat;
    @OneToMany(mappedBy = "warden", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();
        if (address != null) fullAddress.append(address);
        if (city != null) fullAddress.append(", ").append(city);
        if (state != null) fullAddress.append(", ").append(state);
        if (pincode != null) fullAddress.append(" - ").append(pincode);
        return fullAddress.toString();
    }
    public String getShiftTiming() {
        if (shiftstarttime != null && shiftendtime != null) {
            return shiftstarttime + " - " + shiftendtime;
        }
        return null;
    }
}
