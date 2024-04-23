package com.iggyzxc.bankingapp.repository;

import com.iggyzxc.bankingapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // A 'query method' that tells spring JPA to find the transactions based on accountId
    // then sort them according to the latest records first
    List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
}
