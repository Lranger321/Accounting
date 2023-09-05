package main.service.resolvers;

import main.dto.TransactionInfo;
import main.dto.TransactionRequest;
import main.dto.TransactionType;
import main.persistance.entity.Transaction;

public interface TransactionResolver {

    Transaction resolveTransaction(TransactionRequest transactionRequest);

    TransactionType getType();
}
