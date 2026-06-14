package com.bytebank.repository;

import com.bytebank.model.Account;
import com.bytebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByUser(User user);
    Optional<Account> findByAccountNoAndStatus(String accountNo, String status);
}
