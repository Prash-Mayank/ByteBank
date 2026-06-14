package com.bytebank.repository;

import com.bytebank.model.Account;
import com.bytebank.model.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit, String> {
    List<FixedDeposit> findByAccount(Account account);
    List<FixedDeposit> findByStatus(String status);
}
