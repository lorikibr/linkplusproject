package com.example.linkplusproject.Pojo;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "originating_account_id")
    private Account originatingAccount;

    @ManyToOne
    @JoinColumn(name = "resulting_account_id")
    private Account resultingAccount;

    private String transactionReason;

    private Date transactionDate;

    public Transaction(double amount, Account originatingAccount, Account resultingAccount, String transactionReason) {
        this.amount = amount;
        this.originatingAccount = originatingAccount;
        this.resultingAccount = resultingAccount;
        this.transactionReason = transactionReason;
        this.transactionDate = new Date();
    }
}
