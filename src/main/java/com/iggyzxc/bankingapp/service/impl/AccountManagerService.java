package com.iggyzxc.bankingapp.service.impl;

import com.iggyzxc.bankingapp.dto.AccountDTO;
import com.iggyzxc.bankingapp.dto.mapper.AccountMapper;
import com.iggyzxc.bankingapp.entity.Account;
import com.iggyzxc.bankingapp.repository.AccountRepository;
import com.iggyzxc.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerService implements AccountService {

    private AccountRepository accountRepository;

    public AccountManagerService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }
}
