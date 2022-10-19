package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
