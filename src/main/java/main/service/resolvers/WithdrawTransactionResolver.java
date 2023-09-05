package main.service.resolvers;

import main.dto.TransactionRequest;
import main.dto.TransactionType;
import main.exception.AccountException;
import main.persistance.entity.Account;
import main.persistance.entity.Transaction;
import main.persistance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawTransactionResolver extends AbstractTransactionResolver {

    @Autowired
    public WithdrawTransactionResolver(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    protected Transaction resolve(TransactionRequest request) {
        Account account = getAccount(request.getAccount());
        if (account.getValue().doubleValue() < request.getValue().doubleValue()) {
            throw new AccountException("Account didn't have enough money to withdraw");
        }
        account.minusValue(request.getValue());
        return createSuccessfulTransaction(request, account);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAW;
    }
}
