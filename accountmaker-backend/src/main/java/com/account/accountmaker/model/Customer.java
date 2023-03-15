package com.account.accountmaker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
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

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Account> account;

    @JsonManagedReference
    public Set<Account> getAccount(){
        return account;
    }

    public Customer(long customerId, String name, String surname){
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
    }
}
