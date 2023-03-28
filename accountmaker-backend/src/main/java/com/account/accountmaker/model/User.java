package com.account.accountmaker.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "myuser")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;

}
