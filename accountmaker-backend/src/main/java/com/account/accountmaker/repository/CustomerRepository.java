package com.account.accountmaker.repository;

import com.account.accountmaker.model.Account;
import com.account.accountmaker.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
