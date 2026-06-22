package com.bytebank.repository;

import com.bytebank.model.Loan;
<<<<<<< HEAD
import com.bytebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
    List<Loan> findByUser(User user);
    List<Loan> findByStatus(String status);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    // TODO: add custom query methods as needed
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
