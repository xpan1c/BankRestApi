package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.users.ThirdParty;
import com.ironhack.bankApi.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Long> {
    Optional<ThirdParty> findByUsername(String username);
}
