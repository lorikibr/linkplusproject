package com.example.linkplusproject.Service;
import com.example.linkplusproject.Pojo.Bank;
import com.example.linkplusproject.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank getBankById(Long bankId) {
        return bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    // Add more methods as needed for bank management operations
}