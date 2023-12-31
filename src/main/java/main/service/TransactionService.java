package main.service;

import main.dto.TransactionInfo;
import main.dto.TransactionRequest;
import main.persistance.entity.TransactionType;
import main.persistance.entity.Transaction;
import main.persistance.repository.TransactionRepository;
import main.service.mapper.TransactionMapper;
import main.service.resolvers.TransactionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper transactionMapper;
    private final Map<TransactionType, TransactionResolver> resolverMap;

    @Autowired
    public TransactionService(@Qualifier("TransactionCachedRepository") TransactionRepository repository,
                              TransactionMapper transactionMapper,
                              Map<TransactionType, TransactionResolver> resolverMap) {
        this.repository = repository;
        this.transactionMapper = transactionMapper;
        this.resolverMap = resolverMap;
    }

    @Transactional
    public TransactionInfo resolveTransaction(TransactionRequest transactionRequest, TransactionType type) {
        Transaction transaction = resolverMap.get(type).resolveTransaction(transactionRequest);
        return transactionMapper.toTransactionInfo(repository.save(transaction));
    }

    public List<TransactionInfo> findAll(String accountNumber) {
        return repository.findAllByAccountNumber(accountNumber).stream()
                .map(transactionMapper::toTransactionInfo)
                .collect(Collectors.toList());
    }
}
