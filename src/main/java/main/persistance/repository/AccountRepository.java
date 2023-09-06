package main.persistance.repository;

import main.persistance.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<Account> findAll();

    List<Account> findAllByName(String name);

    Account save(Account account);

    Optional<Account> findByAccountNumber(String accountNumber);
}
