package com.account.accountmaker.controller;

import com.account.accountmaker.enumeration.RestAPIResponseStatus;
import com.account.accountmaker.model.Account;
import com.account.accountmaker.request.CreateAccountRequest;
import com.account.accountmaker.service.AccountService;
import com.account.accountmaker.util.Util;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/account")
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Object> createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        try {
            log.info("Account creation request received.");
            Account account = accountService.createAccount(createAccountRequest);
            log.info("Account created successfully");
            return Util.responseEntity("Account created successfully", RestAPIResponseStatus.Success, account);
        }catch (Exception ex){
            log.error("Error occurred while creating account for customerId: " + createAccountRequest.getCustomerId() + " with Error: " + ex.getMessage());
            throw ex;
        }
    }
}
