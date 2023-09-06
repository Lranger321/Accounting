package main.persistance.repository;

import main.persistance.entity.Transaction;

import java.util.List;

public interface TransactionRepository {

    Transaction save(Transaction transaction);

    List<Transaction> findAllByAccountNumber(String accountNumber);
}
