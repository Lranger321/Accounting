package main.persistance.repository.impl.jpa;

import main.persistance.entity.Transaction;
import main.persistance.repository.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JPATransactionRepository extends TransactionRepository, JpaRepository<Transaction, UUID> {
}
