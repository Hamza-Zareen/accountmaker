package com.account.accountmaker.service;

import com.account.accountmaker.model.Account;
import com.account.accountmaker.model.Customer;
import com.account.accountmaker.model.Transaction;
import com.account.accountmaker.modeldto.AccountDto;
import com.account.accountmaker.repository.AccountRepository;
import com.account.accountmaker.request.CreateAccountRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;

@Service
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    ModelMapper modelMapper;

    private AccountRepository accountRepository;

    private CustomerService customerService;

    private TransactionService transactionService;


    public AccountService(AccountRepository accountRepository, CustomerService customerService, TransactionService transactionService){
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    public Account createAccount(CreateAccountRequest createAccountRequest){
        log.info("Account creation started.");
        Customer customer = customerService.getCustomerById(createAccountRequest.getCustomerId());
        Account account= new Account();
        account.setInitialCredit(createAccountRequest.getInitialCredit());
        account.setCustomer(customer);
        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            if(account.getTransaction() == null){
                account.setTransaction(new HashSet<>());
            }
            account.getTransaction().add(transactionService.createTransaction(createAccountRequest));
        }
        accountRepository.save(account);
        log.info("Account created successfully.");
        return account;
    }

}
