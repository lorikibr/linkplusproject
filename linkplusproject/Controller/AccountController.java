package com.example.linkplusproject.Controller;
import com.example.linkplusproject.Pojo.Account;
import com.example.linkplusproject.Pojo.Transaction;
import com.example.linkplusproject.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<String> createAccount(
            @RequestParam String accountName,
            @RequestParam Long bankId,
            @RequestParam double initialBalance) {

        accountService.createAccountAndAssociateWithBank(accountName, bankId, initialBalance);
        return ResponseEntity.ok("Account created and associated with the bank successfully.");
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Double> getAccountBalanceById(@PathVariable Long accountId) {
        double balance = accountService.getAccountById(accountId).getBalance();
        return ResponseEntity.ok(balance);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<Account> depositMoney(@PathVariable Long accountId, @RequestParam double amount) {
        Account account = accountService.depositMoney(accountId, amount);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<Account> withdrawMoney(@PathVariable Long accountId, @RequestParam double amount) {
        Account account = accountService.withdrawMoney(accountId, amount);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{fromAccountId}/{toAccountId}/transferWithPercentage")
    public ResponseEntity<Account> sendMoneyP(
            @PathVariable Long fromAccountId,
            @PathVariable Long toAccountId,
            @RequestParam double amount) {

        Account fromAccount = accountService.transferWithPercentage(fromAccountId, toAccountId, amount);

        return ResponseEntity.ok(fromAccount);
    }

    @PutMapping("/{fromAccountId}/{toAccountId}/transferWithFlat")
    public ResponseEntity<Account> sendMoneyF(
            @PathVariable Long fromAccountId,
            @PathVariable Long toAccountId,
            @RequestParam double amount) {

        Account fromAccount = accountService.transferWithFlat(fromAccountId, toAccountId, amount);

        return ResponseEntity.ok(fromAccount);
    }



    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable Long accountId) {
        List<Transaction> transactions = accountService.getAccountTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }



}
