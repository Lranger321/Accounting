package main.service.resolvers;

import main.dto.TransactionRequest;
import main.persistance.entity.Account;
import main.persistance.entity.Transaction;
import main.persistance.entity.TransactionStatus;
import main.persistance.entity.TransactionType;
import main.persistance.repository.AccountRepository;
import main.service.DateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositTransactionResolverTest {

    private static final String ACCOUNT_NUMBER = "12312312331";

    private static final Account ACCOUNT = Account.builder()
            .name("123")
            .accountNumber(ACCOUNT_NUMBER)
            .value(BigDecimal.ZERO)
            .build();
    private static final Transaction TRANSACTION = Transaction.builder()
            .value(BigDecimal.TEN)
            .account(ACCOUNT)
            .type(TransactionType.DEPOSIT)
            .status(TransactionStatus.SUCCESS)
            .error(null)
            .build();
    private static final TransactionRequest request = TransactionRequest.builder()
            .account(ACCOUNT_NUMBER)
            .pin("1234")
            .value(BigDecimal.TEN)
            .build();
    private TransactionResolver resolver;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() throws Exception {
        resolver = new DepositTransactionResolver(accountRepository, new DateService());
        when(accountRepository.findByAccountNumber(ACCOUNT_NUMBER)).thenReturn(Optional.of(ACCOUNT));
    }

    @Test
    void resolve() {
        Transaction actualTransaction = resolver.resolveTransaction(request);
        assertAll(
                () -> assertEquals(TRANSACTION.getAccount(), actualTransaction.getAccount()),
                () -> assertEquals(TRANSACTION.getType(), actualTransaction.getType()),
                () -> assertEquals(TRANSACTION.getStatus(), actualTransaction.getStatus()),
                () -> assertEquals(TRANSACTION.getValue(), actualTransaction.getValue()),
                () -> assertEquals(BigDecimal.TEN, ACCOUNT.getValue())
        );
    }
}