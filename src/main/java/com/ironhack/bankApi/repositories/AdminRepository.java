package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.Admin;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
