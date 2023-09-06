package main.persistance.entity.cached;

import main.persistance.entity.Account;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Account")
public class AccountCached extends Account {
}
