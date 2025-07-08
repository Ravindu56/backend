package com.hostel.management.entity;

import com.hostel.management.entity.enums.ComplaintCategory;
import com.hostel.management.entity.enums.ComplaintStatus;
import com.hostel.management.entity.enums.Priority;
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
@Table(name = "ComplaintTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
    @Id
    @Size(max = 15, message = "Complaint ID must not exceed 15 characters")
    @Column(length = 15)
    private String complaintID;
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    @Column(nullable = false, length = 200)
    private String title;
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(nullable = false, length = 1000)
    private String description;
    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintCategory category;
    @NotNull(message = "Priority is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
    private ComplaintStatus status = ComplaintStatus.PENDING;
    @Size(max = 1000, message = "Resolution must not exceed 1000 characters")
    private String resolution;
    @Size(max = 500, message = "Remarks must not exceed 500 characters")
    private String remarks;
    private LocalDateTime resolveddate;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdat;
    @UpdateTimestamp
    private LocalDateTime updatedat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignedto")
    private Warden assignedTo;

    public boolean isResolved() {
        return status == ComplaintStatus.RESOLVED;
    }

    public boolean isPending() {
        return status == ComplaintStatus.PENDING;
    }

    public boolean isInProgress() {
        return status == ComplaintStatus.IN_PROGRESS;
    }

    public long getDaysOpen() {
        LocalDateTime endTime = resolveddate != null ? resolveddate : LocalDateTime.now();
        return java.time.temporal.ChronoUnit.DAYS.between(createdat, endTime);
    }

    public String getComplaintSummary() {
        return title + " (" + category.getDisplayName() + " - " + priority.getDisplayName() + ")";
    }

    public boolean isHighPriority() {
        return priority == Priority.HIGH || priority == Priority.URGENT;
    }
}
