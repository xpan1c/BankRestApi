package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
