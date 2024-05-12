package com.example.linkplusproject.Service;

import com.example.linkplusproject.Pojo.Account;
import com.example.linkplusproject.Pojo.Bank;
import com.example.linkplusproject.Pojo.Transaction;
import com.example.linkplusproject.Repository.AccountRepository;
import com.example.linkplusproject.Repository.BankRepository;
import com.example.linkplusproject.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }


    public Account createAccount(Account account) {
        // Additional business logic or validation can be added here
        return accountRepository.save(account);
    }

    public void createAccountAndAssociateWithBank(String accountName, Long bankId, double initialBalance) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        Account account = new Account(accountName, bank);
        account.setBalance(initialBalance); // Set the initial balance
        accountRepository.save(account);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


    public Account depositMoney(Long accountId, double amount) {
        Account account = getAccountById(accountId);
        account.deposit(amount);
        // Create and save a new transaction for the deposit
        Transaction transaction = new Transaction(amount, account, account, "Deposit");
        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public Account withdrawMoney(Long accountId, double amount) {
        Account account = getAccountById(accountId);
        account.withdraw(amount);

        // Create and save a new transaction for the withdrawal
        Transaction transaction = new Transaction(-amount, account, account, "Withdrawal");
        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public Account transferWithPercentage(Long fromAccountId,Long toAccountId, double amount) {
        Account fromAccount = getAccountById(fromAccountId);
        Bank bank = fromAccount.getBank();

        fromAccount.withdraw(amount+(amount*bank.getTransactionPercentageFeeAmount()));

        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + amount );
        bank.setTotalTransactionFeeAmount(  bank.getTotalTransactionFeeAmount() + amount*bank.getTransactionPercentageFeeAmount() );


        Account toAccount = getAccountById(toAccountId);
        toAccount.deposit(amount);
        accountRepository.save(toAccount);
        // Create and save a new transaction for the deposit
        Transaction transaction = new Transaction(amount, fromAccount, toAccount, "Transfer");
        transactionRepository.save(transaction);

        return accountRepository.save(fromAccount);
    }
    public Account transferWithFlat(Long fromAccountId,Long toAccountId, double amount) {
        Account fromAccount = getAccountById(fromAccountId);
        Bank bank = fromAccount.getBank();
        fromAccount.withdraw(amount+bank.getTransactionFlatFeeAmount());


        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + amount );
        bank.setTotalTransactionFeeAmount(  bank.getTotalTransactionFeeAmount() + bank.getTransactionFlatFeeAmount() );


        Account toAccount = getAccountById(toAccountId);
        toAccount.deposit(amount);
        accountRepository.save(toAccount);
        // Create and save a new transaction for the deposit
        Transaction transaction = new Transaction(amount, fromAccount, toAccount, "Transfer");
        transactionRepository.save(transaction);

        return accountRepository.save(fromAccount);
    }

    public List<Transaction> getAccountTransactions(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }



}
