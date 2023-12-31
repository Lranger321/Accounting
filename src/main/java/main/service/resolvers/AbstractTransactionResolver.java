package main.service.resolvers;

import main.dto.TransactionRequest;
import main.exception.AccountNotFoundException;
import main.persistance.entity.Account;
import main.persistance.entity.Transaction;
import main.persistance.entity.TransactionStatus;
import main.persistance.repository.AccountRepository;
import main.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class AbstractTransactionResolver implements TransactionResolver {

    private final AccountRepository accountRepository;
    private final DateService dateService;

    @Autowired
    public AbstractTransactionResolver(@Qualifier("AccountCachedRepository") AccountRepository accountRepository,
                                       DateService dateService) {
        this.accountRepository = accountRepository;
        this.dateService = dateService;
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
        if (accountNumber == null) {
            throw new AccountNotFoundException("Account number is null");
        }
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new AccountNotFoundException("Account with account number <%s> not found", accountNumber);
        }
    }

    protected Transaction createSuccessfulTransaction(TransactionRequest request, Account account) {
        return Transaction.builder()
                .account(account)
                .value(request.getValue())
                .createdAt(dateService.getDate())
                .status(TransactionStatus.SUCCESS)
                .type(getType())
                .build();
    }

    protected Transaction createFailedTransaction(TransactionRequest request, Account account, Exception e) {
        return Transaction.builder()
                .account(account)
                .value(request.getValue())
                .error(e.getMessage())
                .createdAt(dateService.getDate())
                .status(TransactionStatus.FAILED)
                .type(getType())
                .build();
    }
}
