package com.iggyzxc.bankingapp.controller;

import com.iggyzxc.bankingapp.dto.AccountDTO;
import com.iggyzxc.bankingapp.dto.TransferFundDTO;
import com.iggyzxc.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add account
    @PostMapping("/")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    // Get an account
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO accountId = accountService.getAccountById(id);
        return ResponseEntity.ok(accountId);
    }

    // Get all account
    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accountList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountList);
    }

    // Deposit an amount
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDTO accountDeposit = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDeposit);
    }

    // Withdraw an amount
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDTO accountWithdraw = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountWithdraw);
    }

    // Delete an account
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is successfully deleted.");
    }

    // Transfer fund
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDTO transferFundDTO) {
        accountService.transferFund(transferFundDTO);
        return ResponseEntity.ok("Successfully transferred funds.");
    }
}
