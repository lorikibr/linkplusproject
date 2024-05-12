package com.example.linkplusproject.Controller;


import com.example.linkplusproject.Pojo.Bank;
import com.example.linkplusproject.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        Bank createdBank = bankService.createBank(bank);
        return new ResponseEntity<>(createdBank, HttpStatus.CREATED);
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long bankId) {
        Bank bank = bankService.getBankById(bankId);
        return ResponseEntity.ok(bank);
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return ResponseEntity.ok(banks);
    }

    // Add more methods as needed for bank management operations
}