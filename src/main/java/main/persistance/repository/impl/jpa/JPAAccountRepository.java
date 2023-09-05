package main.persistance.repository.impl.jpa;

import main.persistance.entity.Account;
import main.persistance.repository.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JPAAccountRepository extends AccountRepository, CrudRepository<Account, UUID> {

    List<Account> findAllByName(String name);
}
