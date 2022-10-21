package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT acc FROM CheckingAccount acc WHERE acc.primaryOwner = ?1")
    List<CheckingAccount> findByMainCheckingAccountList(AccountHolder accountHolder);

    @Query("SELECT acc FROM StudentCheckingAccount acc WHERE acc.primaryOwner = ?1")
    List<StudentCheckingAccount> findByMainStudentCheckingAccountList(AccountHolder accountHolder);
    @Query("SELECT acc FROM Savings acc WHERE acc.primaryOwner = ?1")
    List<Savings> findByMainSavingsAccountList(AccountHolder accountHolder);
    @Query("SELECT acc FROM CreditCard acc WHERE acc.primaryOwner = ?1")
    List<CreditCard> findByCreditCardList(AccountHolder accountHolder);

}
