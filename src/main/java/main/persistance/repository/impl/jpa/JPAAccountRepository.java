package main.persistance.repository.impl.jpa;

import main.persistance.entity.Account;
import main.persistance.repository.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JPAAccountRepository extends AccountRepository, JpaRepository<Account, UUID> {
}
