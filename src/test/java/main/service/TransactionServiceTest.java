package main.service;

import main.dto.TransactionInfo;
import main.dto.TransactionRequest;
import main.persistance.entity.Transaction;
import main.persistance.entity.TransactionType;
import main.persistance.repository.TransactionRepository;
import main.service.mapper.TransactionMapper;
import main.service.resolvers.DepositTransactionResolver;
import main.service.resolvers.TransactionResolver;
import main.service.resolvers.TransferTransactionResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    private static final Map<TransactionType, TransactionResolver> RESOLVER_MAP = new HashMap<>();
    private static final TransactionMapper MAPPER = Mappers.getMapper(TransactionMapper.class);

    private static final Transaction TRANSACTION = Transaction.builder()
            .type(TransactionType.DEPOSIT)
            .build();

    @Mock
    private DepositTransactionResolver depositTransactionResolver;

    @Mock
    private TransferTransactionResolver transferTransactionResolver;

    @Mock
    private TransactionRepository repository;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        RESOLVER_MAP.put(TransactionType.DEPOSIT, depositTransactionResolver);
        RESOLVER_MAP.put(TransactionType.TRANSFER, transferTransactionResolver);
        transactionService = new TransactionService(repository, MAPPER, RESOLVER_MAP);
    }

    @Test
    void resolveTransaction() {
        when(depositTransactionResolver.resolveTransaction(any())).thenReturn(TRANSACTION);
        when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        assertEquals(TransactionType.DEPOSIT,
                transactionService.resolveTransaction(new TransactionRequest(), TransactionType.DEPOSIT).getType());
    }

    @Test
    void findAll() {
        List<Transaction> transactions = List.of(TRANSACTION);
        List<TransactionInfo> transactionInfos = List.of(MAPPER.toTransactionInfo(TRANSACTION));
        when(repository.findAllByAccountNumber("1234")).thenReturn(transactions);
        assertEquals(transactionInfos, transactionService.findAll("1234"));
    }
}
