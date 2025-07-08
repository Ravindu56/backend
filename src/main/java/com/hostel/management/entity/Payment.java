package com.hostel.management.entity;

import com.hostel.management.entity.enums.PaymentMethod;
import com.hostel.management.entity.enums.PaymentStatus;
import com.hostel.management.entity.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PaymentTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @Size(max = 15, message = "Payment ID must not exceed 15 characters")
    @Column(length = 15)
    private String paymentID;
    @NotNull(message = "Payment type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymenttype;
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
    @Digits(integer = 8, fraction = 2, message = "Invalid amount format")
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;
    @DecimalMin(value = "0.0", message = "Penalty amount cannot be negative")
    @Digits(integer = 8, fraction = 2, message = "Invalid penalty amount format")
    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal penaltyamount = BigDecimal.ZERO;
    @Formula("amount + COALESCE(penaltyamount, 0)")
    private BigDecimal totalamount;
    @NotNull(message = "Due date is required")
    private LocalDate duedate;
    private LocalDate paymentdate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
    private PaymentStatus status = PaymentStatus.PENDING;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentmethod;
    @Size(max = 100, message = "Transaction reference must not exceed 100 characters")
    private String transactionreference;
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
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
    public boolean isOverdue() {
        return status == PaymentStatus.PENDING && duedate.isBefore(LocalDate.now());
    }
    public boolean isPaid() {
        return status == PaymentStatus.PAID;
    }
    public BigDecimal calculateTotalAmount() {
        return amount.add(penaltyamount != null ? penaltyamount : BigDecimal.ZERO);
    }
    public long getDaysOverdue() {
        if (isOverdue()) {
            return java.time.temporal.ChronoUnit.DAYS.between(duedate, LocalDate.now());
        }
        return 0;
    }
    public String getPaymentSummary() {
        return paymenttype.getDisplayName() + " - ₹" + calculateTotalAmount() + " (" + status.getDisplayName() + ")";
    }
}
