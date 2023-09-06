package main.service;

import main.dto.AccountCreateRequest;
import main.dto.AccountDTO;
import main.persistance.entity.Account;
import main.persistance.repository.AccountRepository;
import main.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountNumberService accountNumberService;
    private final PinService pinService;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper,
                          AccountNumberService accountNumberService, PinService pinService) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.accountNumberService = accountNumberService;
        this.pinService = pinService;
    }

    public AccountDTO createAccount(AccountCreateRequest request) {
        Account account = Account.builder()
                .name(request.getName())
                .value(BigDecimal.ZERO)
                .accountNumber(accountNumberService.createAccountNumber())
                .pin(pinService.getSecretPin(request.getPin()))
                .build();
        return accountMapper.toAccountDto(accountRepository.save(account));
    }

    public List<AccountDTO> findAll(String name) {
        return accountRepository.findAllByName(name).stream()
                .map(accountMapper::toAccountDto)
                .collect(Collectors.toList());
    }

}
