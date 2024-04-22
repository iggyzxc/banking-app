package com.iggyzxc.bankingapp.service;


import com.iggyzxc.bankingapp.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(Long id);

    List<AccountDTO> getAllAccounts();

}
