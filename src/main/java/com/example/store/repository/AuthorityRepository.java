package com.example.store.repository;

import com.example.store.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entity.Authority;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    @Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
    List<Authority> AuthoritiesOf(List<Account> accounts);
}
