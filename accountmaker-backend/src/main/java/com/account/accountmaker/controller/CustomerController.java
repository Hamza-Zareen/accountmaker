package com.account.accountmaker.controller;

import com.account.accountmaker.enumeration.RestAPIResponseStatus;
import com.account.accountmaker.model.Account;
import com.account.accountmaker.model.Customer;
import com.account.accountmaker.request.CreateAccountRequest;
import com.account.accountmaker.service.AccountService;
import com.account.accountmaker.service.CustomerService;
import com.account.accountmaker.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<Object> getCustomer(@PathVariable long customerId){
        try {
            log.info("Getting customer by id.");
            Customer customer = customerService.getCustomerById(customerId);
            log.info("Customer retrieved successfully.");
            return Util.responseEntity("Customer retrieved successfully", RestAPIResponseStatus.Success, customer);
        }catch (Exception ex){
            log.error("Error occurred while getting customer by id: " + customerId + " with Error: " + ex.getMessage());
            throw ex;
        }
    }

    @GetMapping
    public ResponseEntity<Object> getCustomers(){
        try {
            log.info("Getting all customer.");
            List<Customer> customers = customerService.getAllCustomer();
            log.info("Customer retrieved successfully.");
            return Util.responseEntity("Customers retrieved successfully", RestAPIResponseStatus.Success, customers);
        }catch (Exception ex){
            log.error("Error occurred while getting customers." + " with Error: " + ex.getMessage());
            throw ex;
        }
    }
}
