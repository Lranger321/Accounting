package main.persistance.repository.impl.memory;

import main.persistance.entity.Account;
import main.persistance.repository.AccountRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("AccountRedisRepository")
public class AccountRedisRepository implements AccountRepository {

    private static final String HASH_KEY = "Account";
    private final RedisTemplate<String, Object> redisTemplate;

    public AccountRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Account> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY)
                .stream()
                .map(acc -> (Account) acc)
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> findAllByName(String name) {
        return redisTemplate.opsForHash().values(HASH_KEY).stream()
                .map(acc -> (Account) acc)
                .filter(acc -> acc.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Account save(Account account) {
        redisTemplate.opsForHash().put(HASH_KEY, account.getId().toString(), account);
        return account;
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return redisTemplate.opsForHash().values(HASH_KEY).stream()
                .map(acc -> (Account) acc)
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

}
