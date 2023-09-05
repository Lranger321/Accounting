package main.service.resolvers;

import main.dto.TransactionRequest;
import main.dto.TransactionType;
import main.exception.AccountException;
import main.persistance.entity.Account;
import main.persistance.entity.Transaction;
import main.persistance.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class TransaferTransactionResolver extends AbstractTransactionResolver {

    public TransaferTransactionResolver(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    public Transaction resolveTransaction(TransactionRequest transactionRequest) {
        return null;
    }

    @Override
    protected Transaction resolve(TransactionRequest request) {
        Account account = getAccount(request.getAccount());
        Account accountTransferTo = getAccount(request.getAccountTransferTo());
        if (account.getValue().doubleValue() < request.getValue().doubleValue()) {
            throw new AccountException("Account didn't have enough money to withdraw");
        }
        account.minusValue(request.getValue());
        accountTransferTo.addValue(request.getValue());
        Transaction successfulTransaction = createSuccessfulTransaction(request, account);
        successfulTransaction.setAccountTransferTo(accountTransferTo);
        return successfulTransaction;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.TRANSFER;
    }
}
