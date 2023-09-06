package main.persistance.entity.cached;

import main.persistance.entity.Transaction;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Transaction")
public class TransactionCached extends Transaction {
}
