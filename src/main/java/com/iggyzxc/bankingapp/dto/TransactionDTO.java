package com.iggyzxc.bankingapp.dto;

import com.iggyzxc.bankingapp.entity.Transaction;

import java.time.LocalDateTime;

public record TransactionDTO(Long id,
                             Long accountId,
                             double amount,
                             Transaction.TransactionType transactionType,
                             LocalDateTime timestamp) {

    public static TransactionDTO fromEntity(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTimestamp()
        );
        return transactionDTO;
    }
}
