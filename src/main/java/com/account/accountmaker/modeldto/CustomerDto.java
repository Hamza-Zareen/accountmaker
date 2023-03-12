package com.account.accountmaker.modeldto;

import com.account.accountmaker.model.Account;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CustomerDto {
    private String customerId;
    private String name;
    private String surname;
    private Set<AccountDto> account;
}
