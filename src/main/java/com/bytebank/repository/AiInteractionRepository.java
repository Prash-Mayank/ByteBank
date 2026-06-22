package com.bytebank.repository;

import com.bytebank.model.AiInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiInteractionRepository extends JpaRepository<AiInteraction, Long> {
    // TODO: add custom query methods as needed
}
