package com.account.accountmaker.service;

import com.account.accountmaker.model.Customer;
import com.account.accountmaker.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public Customer getCustomerById(Long customerId){
        log.info("Getting customer by id.");
        return customerRepository.findById(customerId).get();
    }

    public List<Customer> getAllCustomer(){
        log.info("Getting all customers.");
        return customerRepository.findAll();
    }

}
