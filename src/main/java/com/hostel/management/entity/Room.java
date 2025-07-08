package com.hostel.management.entity;

import com.hostel.management.entity.enums.ACType;
import com.hostel.management.entity.enums.RoomStatus;
import com.hostel.management.entity.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "RoomTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @Size(max = 10, message = "Room ID must not exceed 10 characters")
    @Column(length = 10)
    private String roomID;
    @NotBlank(message = "Room number is required")
    @Size(max = 10, message = "Room number must not exceed 10 characters")
    @Column(unique = true, nullable = false, length = 10)
    private String roomnumber;
    @NotNull(message = "Room type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType roomtype;
    @NotNull(message = "AC type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ACType actype;
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 4, message = "Capacity must not exceed 4")
    @Column(nullable = false)
    private Integer capacity;
    @Min(value = 0, message = "Occupied count cannot be negative")
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer occupiedcount = 0;
    @Min(value = 0, message = "Floor number cannot be negative")
    private Integer floornumber;
    @Size(max = 10, message = "Wing must not exceed 10 characters")
    private String wing;
    @NotNull(message = "Monthly rent is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly rent must be positive")
    @Digits(integer = 8, fraction = 2, message = "Invalid monthly rent format")
    @Column(precision = 10, scale = 2)
    private BigDecimal monthlyrent;
    @NotNull(message = "Security deposit is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Security deposit must be positive")
    @Digits(integer = 8, fraction = 2, message = "Invalid security deposit format")
    @Column(precision = 10, scale = 2)
    private BigDecimal securitydeposit;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'AVAILABLE'")
    private RoomStatus roomstatus = RoomStatus.AVAILABLE;
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdat;
    @UpdateTimestamp
    private LocalDateTime updatedat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wardenid")
    private Warden warden;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visitor> visitors;

    public boolean isAvailable() {
        return roomstatus == RoomStatus.AVAILABLE && occupiedcount < capacity;
    }

    public boolean isFull() {
        return occupiedcount >= capacity;
    }

    public int getAvailableSpots() {
        return capacity - occupiedcount;
    }

    public String getRoomDetails() {
        return roomnumber + " (" + roomtype.getDisplayName() + ", " + actype.getDisplayName() + ")";
    }

    public BigDecimal getTotalDeposit() {
        return monthlyrent.add(securitydeposit);
    }
}
