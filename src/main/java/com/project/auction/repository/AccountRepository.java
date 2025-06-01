package com.project.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.auction.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String attr0);
}
