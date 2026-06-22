package com.bytebank.repository;

<<<<<<< HEAD
import com.bytebank.model.Account;
import com.bytebank.model.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit, String> {
    List<FixedDeposit> findByAccount(Account account);
    List<FixedDeposit> findByStatus(String status);
=======
import com.bytebank.model.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit, Long> {
    // TODO: add custom query methods as needed
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
