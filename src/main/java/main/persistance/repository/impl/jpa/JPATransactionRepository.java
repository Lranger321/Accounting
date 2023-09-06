package main.persistance.repository.impl.jpa;

import main.persistance.entity.Transaction;
import main.persistance.repository.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JPATransactionRepository extends TransactionRepository, JpaRepository<Transaction, UUID> {

    @Override
    @Query("select t from Transaction t where t.account.accountNumber = ?1")
    List<Transaction> findAllByAccountNumber(String accountNumber);
}
