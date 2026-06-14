package com.bytebank.repository;

import com.bytebank.model.Beneficiary;
import com.bytebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findByOwner(User owner);
    List<Beneficiary> findByOwnerAndStatus(User owner, String status);
}
