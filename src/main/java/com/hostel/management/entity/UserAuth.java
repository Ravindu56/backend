package com.hostel.management.entity;

import com.hostel.management.entity.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "userauth")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must not exceed 50 characters")
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @NotBlank(message = "Password hash is required")
    @Size(max = 255, message = "Password hash must not exceed 255 characters")
    @Column(nullable = false, length = 255)
    private String passwordhash;
    @NotBlank(message = "Salt is required")
    @Size(max = 32, message = "Salt must not exceed 32 characters")
    @Column(nullable = false, length = 32)
    private String salt;
    @NotNull(message = "User role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userrole;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isactive = true;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdat;
    @UpdateTimestamp
    private LocalDateTime updatedat;
    private LocalDateTime lastlogin;
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer failedattempts = 0;
    private LocalDateTime lockeduntil;

    public boolean isAccountLocked() {
        return lockeduntil != null && lockeduntil.isAfter(LocalDateTime.now());
    }

    public boolean isAccountActive() {
        return isactive != null && isactive;
    }
}
