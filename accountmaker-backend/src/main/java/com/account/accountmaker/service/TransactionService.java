package com.account.accountmaker.service;

import com.account.accountmaker.model.Account;
import com.account.accountmaker.model.Customer;
import com.account.accountmaker.model.Transaction;
import com.account.accountmaker.repository.TransactionRepository;
import com.account.accountmaker.request.CreateAccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(CreateAccountRequest createAccountRequest){
        log.info("Transaction Creation Started...");
        Transaction transaction= new Transaction();
        transaction.setBalance(createAccountRequest.getInitialCredit());
        Transaction transactionCreated = transactionRepository.save(transaction);
        log.info("Transaction Created Successfully");
        return transactionCreated;
    }
}
