package com.account.accountmaker.data;

import com.account.accountmaker.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<UserInput, User> {

  private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

  @Override
  public User process(final UserInput userInput) throws Exception {
    final long customerId = userInput.getId();

    final User transformedUser = new User(customerId , userInput.getUserName(), userInput.getPassword(), userInput.isActive(), userInput.getRoles());

    log.info("Converting (" + userInput + ") into (" + transformedUser + ")");

    return transformedUser;
  }

}