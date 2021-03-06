package com.wbl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbl.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}