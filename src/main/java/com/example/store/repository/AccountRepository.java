package com.example.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entity.Account;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Override
    List<Account> findAll();

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    @Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN('DIRE', 'STAF')")
    List<Account> getAdministrators();

}
