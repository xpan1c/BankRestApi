package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount,Long> {
}
