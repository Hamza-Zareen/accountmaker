package com.account.accountmaker.service;

import com.account.accountmaker.model.Customer;
import com.account.accountmaker.modeldto.CustomerDto;
import com.account.accountmaker.repository.CustomerRepository;
//import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

//    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public Customer getCustomerById(Long customerId){
        log.info("Getting Customer by id.");
        return customerRepository.findById(customerId).get();
    }

}
