package main.service.resolvers;

import main.dto.TransactionRequest;
import main.dto.TransactionType;
import main.persistance.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public class WithdrawTransactionResolver implements TransactionResolver {

    @Override
    public Transaction resolveTransaction(TransactionRequest transactionRequest) {
        return null;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAW;
    }
}
