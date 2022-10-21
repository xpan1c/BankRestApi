package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
