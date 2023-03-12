package com.account.accountmaker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public Customer(long customerId, String name,String surname){
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
    }
}
