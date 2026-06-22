package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
=======
import lombok.Data;
>>>>>>> 093ee2d (ByteBank V2 project stucture)
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

<<<<<<< HEAD
@Entity
@Table(name = "emi_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmiSchedule {
=======
/** emi_schedule table — full amortisation schedule per loan. */
@Entity
@Table(name = "emi_schedule")
@Data
public class EmiSchedule {

>>>>>>> 093ee2d (ByteBank V2 project stucture)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emi_id")
    private Long emiId;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Boolean paid = false;
=======
    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Column(name = "due_date")
    private LocalDate dueDate;

    private BigDecimal amount;

    private boolean paid;
>>>>>>> 093ee2d (ByteBank V2 project stucture)

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

<<<<<<< HEAD
    @Column(name = "balance_after", precision = 15, scale = 2)
=======
    @Column(name = "balance_after")
>>>>>>> 093ee2d (ByteBank V2 project stucture)
    private BigDecimal balanceAfter;
}
