package com.example.linkplusproject.Pojo;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;


    private double balance;

    public Account(String name, Bank bank) {
        this.name = name;
        this.bank = bank;

    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (balance - amount < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
    }
}