package com.bytebank.repository;

import com.bytebank.model.EmiSchedule;
import com.bytebank.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmiScheduleRepository extends JpaRepository<EmiSchedule, Long> {
    List<EmiSchedule> findByLoan(Loan loan);
    List<EmiSchedule> findByPaidFalseAndDueDateLessThanEqual(LocalDate date);
}
