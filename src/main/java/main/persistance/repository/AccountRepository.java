package main.persistance.repository;

import main.persistance.entity.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAllByName(String name);

    Account save(Account account);
}
