package main.persistance.repository.impl.proxy;

import main.persistance.entity.Transaction;
import main.persistance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository("TransactionCachedRepository")
public class TransactionCachedRepository implements TransactionRepository {

    private final TransactionRepository dbRepository;
    private final TransactionRepository cachedRepository;

    @Autowired
    public TransactionCachedRepository(@Qualifier("JPATransactionRepository") TransactionRepository dbRepository,
                                       @Qualifier("TransactionRedisRepository") TransactionRepository cachedRepository) {
        this.dbRepository = dbRepository;
        this.cachedRepository = cachedRepository;
    }

    @Override
    public List<Transaction> findAll() {
        try {
            return dbRepository.findAll();
        } catch (Exception e) {
            return cachedRepository.findAll();
        }
    }

    @Override
    public Transaction save(Transaction transaction) {
        return cachedRepository.save(dbRepository.save(transaction));
    }

    @Override
    public List<Transaction> findAllByAccountNumber(String accountNumber) {
        try {
            return dbRepository.findAllByAccountNumber(accountNumber);
        } catch (Exception e) {
            return cachedRepository.findAllByAccountNumber(accountNumber);
        }
    }

    @PostConstruct
    public void updateCached() {
        dbRepository.findAll()
                .forEach(cachedRepository::save);
    }
}
