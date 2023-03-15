package com.account.accountmaker.data;

import com.account.accountmaker.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class CustomerItemProcessor implements ItemProcessor<CustomerInput, Customer> {

  private static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

  @Override
  public Customer process(final CustomerInput customerInput) throws Exception {
    final String name = customerInput.getName().toUpperCase();
    final String surname = customerInput.getSurname().toUpperCase();
    final long customerId = customerInput.getCustomerId();

    final Customer transformedPerson = new Customer(customerId , name, surname);

    log.info("Converting (" + customerInput + ") into (" + transformedPerson + ")");

    return transformedPerson;
  }

}