package com.iggyzxc.bankingapp.service.impl;

import com.iggyzxc.bankingapp.dto.AccountDTO;
import com.iggyzxc.bankingapp.dto.TransferFundDTO;
import com.iggyzxc.bankingapp.dto.mapper.AccountMapper;
import com.iggyzxc.bankingapp.entity.Account;
import com.iggyzxc.bankingapp.entity.Transaction;
import com.iggyzxc.bankingapp.exception.AccountException;
import com.iggyzxc.bankingapp.repository.AccountRepository;
import com.iggyzxc.bankingapp.repository.TransactionRepository;
import com.iggyzxc.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountManagerService implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    public static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    public static final String TRANSACTION_TYPE_TRANSFER = "TRANSFER";

    public AccountManagerService(AccountRepository accountRepository,
                                 TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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

        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transactionRepository.save(transaction);

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

        account.setBalance(account.getBalance() - amount);
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
        transactionRepository.save(transaction);

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

        // Transfer amount validation
        if (sourceAccount.getBalance() < transferFundDTO.amount()) {
            throw new RuntimeException("Insufficient balance.");
        }

        // Debit/Subtract the amount from sourceAccount object
        sourceAccount.setBalance(
                sourceAccount.getBalance() - transferFundDTO.amount());

        // Credit/Add the amount to destinationAccount object
        destinationAccount.setBalance(
                destinationAccount.getBalance() + transferFundDTO.amount());

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction();
        transaction.setAccountId(transferFundDTO.sourceAccountId());
        transaction.setAmount(transferFundDTO.amount());
        transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
        transactionRepository.save(transaction);
    }
}
