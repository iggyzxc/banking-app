package com.iggyzxc.bankingapp.controller;

import com.iggyzxc.bankingapp.dto.AccountDTO;
import com.iggyzxc.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add account REST API
    @PostMapping("/")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    // Get an account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO accountId = accountService.getAccountById(id);
        return ResponseEntity.ok(accountId);
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accountList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountList);
    }

}
