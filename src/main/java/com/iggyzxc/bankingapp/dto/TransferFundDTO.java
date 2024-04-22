package com.iggyzxc.bankingapp.dto;

public record TransferFundDTO(Long sourceAccountId,
                              Long destinationAccountId,
                              double amount) {
}
