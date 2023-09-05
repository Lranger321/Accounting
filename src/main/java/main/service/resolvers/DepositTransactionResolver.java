package main.service.resolvers;

import main.dto.TransactionRequest;
import main.dto.TransactionType;
import main.persistance.entity.Account;
import main.persistance.entity.Transaction;
import main.persistance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositTransactionResolver extends AbstractTransactionResolver {

    @Autowired
    public DepositTransactionResolver(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    protected Transaction resolve(TransactionRequest request) {
        Account account = getAccount(request.getAccount());
        account.addValue(request.getValue());
        return createSuccessfulTransaction(request, account);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.DEPOSIT;
    }
}
