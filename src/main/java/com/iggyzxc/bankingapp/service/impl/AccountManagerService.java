package com.iggyzxc.bankingapp.service.impl;

import com.iggyzxc.bankingapp.dto.AccountDTO;
import com.iggyzxc.bankingapp.dto.TransferFundDTO;
import com.iggyzxc.bankingapp.dto.mapper.AccountMapper;
import com.iggyzxc.bankingapp.entity.Account;
import com.iggyzxc.bankingapp.exception.AccountException;
import com.iggyzxc.bankingapp.repository.AccountRepository;
import com.iggyzxc.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountException("Account does not exist."));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts
                .stream()
                .map(AccountMapper::mapToAccountDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountException("Account does not exist."));
        double totalBalance = account.getBalance() + amount;
        account.setBalance(totalBalance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountException("Account does not exist."));
        if(account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance.");
        }
        double totalBalance = account.getBalance() - amount;
        account.setBalance(totalBalance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountException("Account does not exist."));
        accountRepository.delete(account);
    }

    @Override
    public void transferFund(TransferFundDTO transferFundDTO) {

        // Account validation for source account
        Account sourceAccount = accountRepository
                .findById(transferFundDTO.sourceAccountId())
                .orElseThrow(() -> new AccountException("Account does not exist."));

        // Account validation for destination account
        Account destinationAccount = accountRepository
                .findById(transferFundDTO.destinationAccountId())
                .orElseThrow(() -> new AccountException("Account does not exist."));

        // Debit/Subtract the amount from sourceAccount object
        sourceAccount.setBalance(
                sourceAccount.getBalance() - transferFundDTO.amount());

        // Credit/Add the amount to destinationAccount object
        destinationAccount.setBalance(
                destinationAccount.getBalance() + transferFundDTO.amount());

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);
    }
}
