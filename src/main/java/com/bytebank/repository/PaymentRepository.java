package com.bytebank.repository;

import com.bytebank.model.Payment;
import com.bytebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByUser(User user);
    Optional<Payment> findByOrderId(String orderId);
}
