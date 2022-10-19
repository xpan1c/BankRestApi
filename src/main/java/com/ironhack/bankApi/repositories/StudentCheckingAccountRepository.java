package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.StudentCheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCheckingAccountRepository extends JpaRepository<StudentCheckingAccount, Long> {
}
