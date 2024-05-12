package com.example.linkplusproject.Pojo;


import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentageFeeAmount;

    public Bank(String name, double totalTransactionFeeAmount, double totalTransferAmount,
                double transactionFlatFeeAmount, double transactionPercentageFeeAmount) {
        this.name = name;
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentageFeeAmount = transactionPercentageFeeAmount;
    }
}