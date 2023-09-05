package main.persistance.repository;

import main.persistance.entity.Transaction;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
}
