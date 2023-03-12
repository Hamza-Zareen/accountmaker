package com.account.accountmaker.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "transaction_id")
    private long transactionId;

    @Column(name= "balance")
    private BigDecimal balance;
}
