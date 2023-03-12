package com.account.accountmaker.modeldto;

import com.account.accountmaker.model.Customer;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AccountDto {
    private BigDecimal initialCredit;
    private String accountId;
    private CustomerDto customer;
    private TransactionDto transaction;
}
