package main.persistance.repository;

import main.persistance.entity.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll();

    Transaction save(Transaction transaction);

    List<Transaction> findAllByAccountNumber(String accountNumber);
}
