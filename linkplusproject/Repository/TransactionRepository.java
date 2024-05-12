package com.example.linkplusproject.Repository;

import com.example.linkplusproject.Pojo.Account;
import com.example.linkplusproject.Pojo.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.originatingAccount.id = :accountId OR t.resultingAccount.id = :accountId")
    List<Transaction> findByAccountId(Long accountId);}
