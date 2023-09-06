package main.persistance.repository.impl.proxy;

import main.persistance.entity.Account;
import main.persistance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Repository("AccountCachedRepository")
public class AccountCachedRepository implements AccountRepository {

    private final AccountRepository dbRepository;
    private final AccountRepository cachedRepository;

    @Autowired
    public AccountCachedRepository(@Qualifier("JPAAccountRepository") AccountRepository dbRepository,
                                   @Qualifier("AccountRedisRepository") AccountRepository cachedRepository) {
        this.dbRepository = dbRepository;
        this.cachedRepository = cachedRepository;
    }

    @Override
    public List<Account> findAll() {
        try {
            return dbRepository.findAll();
        } catch (Exception e) {
            return cachedRepository.findAll();
        }
    }

    @Override
    public List<Account> findAllByName(String name) {
        try {
            return dbRepository.findAllByName(name);
        } catch (Exception e) {
            return cachedRepository.findAllByName(name);
        }
    }

    @Override
    public Account save(Account account) {
        return cachedRepository.save(dbRepository.save(account));
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        try {
            return dbRepository.findByAccountNumber(accountNumber);
        } catch (Exception e) {
            return cachedRepository.findByAccountNumber(accountNumber);
        }
    }

    @PostConstruct
    public void updateCached() {
        dbRepository.findAll()
                .forEach(cachedRepository::save);
    }
}
