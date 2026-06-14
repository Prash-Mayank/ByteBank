package com.bytebank.repository;

import com.bytebank.model.Loan;
import com.bytebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
    List<Loan> findByUser(User user);
    List<Loan> findByStatus(String status);
}
