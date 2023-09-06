package main.service.resolvers;

import main.dto.TransactionRequest;
import main.exception.AccountException;
import main.persistance.entity.Account;
import main.persistance.entity.Transaction;
import main.persistance.entity.TransactionType;
import main.persistance.repository.AccountRepository;
import main.service.DateService;
import main.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WithdrawTransactionResolver extends AbstractTransactionResolver {

    private final PinService pinService;

    @Autowired
    public WithdrawTransactionResolver(@Qualifier("AccountCachedRepository") AccountRepository accountRepository,
                                       DateService dateService, PinService pinService) {
        super(accountRepository, dateService);
        this.pinService = pinService;
    }

    @Override
    protected Transaction resolve(TransactionRequest request) {
        Account account = getAccount(request.getAccount());
        if (account.getValue().doubleValue() < request.getValue().doubleValue()) {
            throw new AccountException("Account didn't have enough money to withdraw");
        }
        if (!pinService.isPinCorrect(request.getPin(), account.getPin())) {
            throw new AccountException("Pin incorrect");
        }
        account.minusValue(request.getValue());
        return createSuccessfulTransaction(request, account);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAW;
    }
}
