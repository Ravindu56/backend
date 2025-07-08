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

@Entity
@Table(name = "AdminTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @Size(max = 10, message = "Admin ID must not exceed 10 characters")
    @Column(length = 10)
    private String adminID;
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
    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;
    @Size(max = 100, message = "Designation must not exceed 100 characters")
    private String designation;
    private LocalDate joiningdate;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdat;
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();
        if (address != null) fullAddress.append(address);
        if (city != null) fullAddress.append(", ").append(city);
        if (state != null) fullAddress.append(", ").append(state);
        if (pincode != null) fullAddress.append(" - ").append(pincode);
        return fullAddress.toString();
    }
}
