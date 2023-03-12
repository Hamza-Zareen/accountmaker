package com.account.accountmaker.repository;

import com.account.accountmaker.model.Account;
import com.account.accountmaker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
