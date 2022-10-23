package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.utils.TransferList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferListRepository extends JpaRepository<TransferList, Long> {
}
