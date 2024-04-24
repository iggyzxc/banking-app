package com.iggyzxc.bankingapp.dto;

import com.iggyzxc.bankingapp.entity.Transaction;

import java.time.LocalDateTime;

public record TransactionDTO(Long id,
                             Long accountId,
                             double amount,
                             Transaction.TransactionType transactionType,
                             LocalDateTime timestamp) {
}
