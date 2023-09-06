package main.service.resolvers;

import main.dto.TransactionRequest;
import main.persistance.entity.Transaction;
import main.persistance.entity.TransactionType;

public interface TransactionResolver {

    Transaction resolveTransaction(TransactionRequest transactionRequest);

    TransactionType getType();
}
