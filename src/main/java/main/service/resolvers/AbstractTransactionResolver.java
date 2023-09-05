package main.service.resolvers;

import main.dto.TransactionRequest;
import main.exception.AccountNotFoundException;
import main.persistance.entity.Account;
import main.persistance.entity.Transaction;
import main.persistance.entity.TransactionStatus;
import main.persistance.repository.AccountRepository;
import main.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class AbstractTransactionResolver implements TransactionResolver {

    private final AccountRepository accountRepository;

    @Autowired
    public AbstractTransactionResolver(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction resolveTransaction(TransactionRequest transactionRequest) {
        try {
            return resolve(transactionRequest);
        } catch (Exception e) {
            return createFailedTransaction(transactionRequest, getAccount(transactionRequest.getAccount()), e);
        }
    }

    protected abstract Transaction resolve(TransactionRequest request);

    protected Account getAccount(String accountNumber) {
        if(accountNumber == null) {
            throw new AccountNotFoundException("Account number is null");
        }
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isPresent()) {
            return account.get();
        } else {
            throw new AccountNotFoundException("Account with account number <%s> not found", accountNumber);
        }
    }

    protected Transaction createSuccessfulTransaction(TransactionRequest request, Account account) {
        return Transaction.builder()
                .account(account)
                .value(request.getValue())
                .status(TransactionStatus.SUCCESS)
                .type(getType())
                .build();
    }

    protected Transaction createFailedTransaction(TransactionRequest request, Account account, Exception e) {
        return Transaction.builder()
                .account(account)
                .value(request.getValue())
                .error(e.getMessage())
                .status(TransactionStatus.FAILED)
                .type(getType())
                .build();
    }
}
