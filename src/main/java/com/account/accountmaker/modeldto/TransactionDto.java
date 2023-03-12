package com.account.accountmaker.modeldto;

import com.account.accountmaker.model.Account;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TransactionDto {
    private String transactionId;
    private BigDecimal balance;
    private AccountDto account;
}
