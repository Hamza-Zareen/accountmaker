package com.account.accountmaker.data;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInput {

    private long id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;

}
