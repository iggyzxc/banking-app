package com.iggyzxc.bankingapp.dto.mapper;

import com.iggyzxc.bankingapp.dto.AccountDTO;
import com.iggyzxc.bankingapp.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDTO accountDTO) {
        Account account = new Account(
                accountDTO.id(),
                accountDTO.accountHolderName(),
                accountDTO.balance()
        );
        return account;
    }

    public static AccountDTO mapToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDTO;
    }
}
