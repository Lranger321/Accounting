package main.service.resolvers;

import main.dto.TransactionRequest;
import main.persistance.entity.TransactionType;
import main.persistance.entity.Account;
import main.persistance.entity.Transaction;
import main.persistance.repository.AccountRepository;
import main.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DepositTransactionResolver extends AbstractTransactionResolver {

    @Autowired
    public DepositTransactionResolver(@Qualifier("AccountCachedRepository") AccountRepository accountRepository,
                                      DateService dateService) {
        super(accountRepository, dateService);
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
