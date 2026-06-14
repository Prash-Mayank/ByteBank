package com.bytebank.repository;

import com.bytebank.model.DailyStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface DailyStatsRepository extends JpaRepository<DailyStats, LocalDate> {
}
