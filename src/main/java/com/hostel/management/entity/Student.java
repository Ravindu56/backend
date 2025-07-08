package com.hostel.management.entity;

import com.hostel.management.entity.enums.Gender;
import com.hostel.management.entity.enums.StudentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "StudentTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Size(max = 10, message = "Student ID must not exceed 10 characters")
    @Column(length = 10)
    private String studentID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", unique = true)
    private UserAuth userAuth;
    @NotBlank(message = "Student roll number is required")
    @Size(max = 20, message = "Student roll number must not exceed 20 characters")
    @Column(unique = true, length = 20)
    private String studentrollnumber;
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
    @Min(value = 1, message = "Duration of stay must be at least 1 month")
    @Max(value = 48, message = "Duration of stay cannot exceed 48 months")
    private Integer durationofstay;
    private LocalDate admissiondate;
    private LocalDate checkoutdate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'ACTIVE'")
    private StudentStatus studentstatus = StudentStatus.ACTIVE;
    @Size(max = 100, message = "Course must not exceed 100 characters")
    private String course;
    @Min(value = 1, message = "Year of study must be at least 1")
    @Max(value = 6, message = "Year of study cannot exceed 6")
    private Integer yearofstudy;
    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;
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
    @Pattern(regexp = "^[0-9]{10}$", message = "Guardian contact must be 10 digits")
    @Column(length = 15)
    private String guardiancontact;
    @Size(max = 100, message = "Guardian name must not exceed 100 characters")
    private String guardianname;
    @Size(max = 50, message = "Guardian relation must not exceed 50 characters")
    private String guardianrelation;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdat;
    @UpdateTimestamp
    private LocalDateTime updatedat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomid")
    private Room room;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visitor> visitors;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Complaint> complaints;
    public boolean isActive() {
        return studentstatus == StudentStatus.ACTIVE;
    }
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();
        if (address != null) fullAddress.append(address);
        if (city != null) fullAddress.append(", ").append(city);
        if (state != null) fullAddress.append(", ").append(state);
        if (pincode != null) fullAddress.append(" - ").append(pincode);
        return fullAddress.toString();
    }
    public boolean hasRoom() {
        return room != null;
    }
    public String getAcademicInfo() {
        return course + " - Year " + yearofstudy + " (" + department + ")";
    }
}
