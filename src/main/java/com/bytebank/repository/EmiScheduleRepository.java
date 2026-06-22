package com.bytebank.repository;

import com.bytebank.model.EmiSchedule;
<<<<<<< HEAD
import com.bytebank.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmiScheduleRepository extends JpaRepository<EmiSchedule, Long> {
    List<EmiSchedule> findByLoan(Loan loan);
    List<EmiSchedule> findByPaidFalseAndDueDateLessThanEqual(LocalDate date);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmiScheduleRepository extends JpaRepository<EmiSchedule, Long> {
    // TODO: add custom query methods as needed
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
