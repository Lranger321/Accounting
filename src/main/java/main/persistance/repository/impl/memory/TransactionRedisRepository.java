package main.persistance.repository.impl.memory;

import main.persistance.entity.Transaction;
import main.persistance.repository.TransactionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("TransactionRedisRepository")
public class TransactionRedisRepository implements TransactionRepository {

    private static final String HASH_KEY = "Transaction";
    private final RedisTemplate<String, Object> redisTemplate;

    public TransactionRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Transaction> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY).stream()
                .map(transaction -> (Transaction) transaction)
                .collect(Collectors.toList());
    }

    @Override
    public Transaction save(Transaction transaction) {
        redisTemplate.opsForHash().put(transaction.getId().toString(), HASH_KEY, transaction);
        return transaction;
    }

    @Override
    public List<Transaction> findAllByAccountNumber(String accountNumber) {
        return redisTemplate.opsForHash().values(HASH_KEY).stream()
                .map(transaction -> (Transaction) transaction)
                .filter(transaction -> transaction.getAccount().getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList());
    }
}
