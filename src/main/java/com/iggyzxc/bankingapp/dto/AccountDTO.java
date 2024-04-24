package com.iggyzxc.bankingapp.dto;

import com.iggyzxc.bankingapp.entity.Account;

public record AccountDTO(Long id,
                         String accountHolderName,
                         double balance) {
    public static Account toEntity(AccountDTO accountDTO) {
        Account account = new Account(
                accountDTO.id(),
                accountDTO.accountHolderName(),
                accountDTO.balance()
        );
        return account;
    }

    public static AccountDTO fromEntity(Account account) {
        AccountDTO accountDTO = new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDTO;
    }
}
