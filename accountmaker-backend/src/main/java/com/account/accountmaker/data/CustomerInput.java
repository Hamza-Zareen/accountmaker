package com.account.accountmaker.data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInput {

  private String name;

  private String surname;

  private long customerId;

}