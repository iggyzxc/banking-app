package com.iggyzxc.bankingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    public enum TransactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // Deposit, Withdraw, or Transfer

    private LocalDateTime timestamp;
}
