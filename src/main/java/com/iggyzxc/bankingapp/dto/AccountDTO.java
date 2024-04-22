package com.iggyzxc.bankingapp.dto;

public record AccountDTO(Long id,
                         String accountHolderName,
                         double balance) {
}
