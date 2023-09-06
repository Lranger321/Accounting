package main.service;

import main.dto.AccountCreateRequest;
import main.dto.AccountDTO;
import main.persistance.entity.Account;
import main.persistance.repository.AccountRepository;
import main.service.mapper.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private static final PinService PIN_SERVICE = new PinService();
    private static final AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);
    private static final Account ACCOUNT = Account.builder()
            .name("name")
            .pin(PIN_SERVICE.getSecretPin("1234"))
            .value(BigDecimal.ZERO)
            .build();
    private static final AccountCreateRequest REQUEST = AccountCreateRequest.builder()
            .name("name")
            .pin("1234")
            .build();


    @Mock
    private AccountService service;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        service = new AccountService(accountRepository, MAPPER, new AccountNumberService(), new PinService());
    }

    @Test
    void createAccount() {
        when(accountRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
        AccountDTO accountDTO = service.createAccount(REQUEST);
        assertAll(
                () -> assertEquals(REQUEST.getName(), accountDTO.getName()),
                () -> assertEquals(BigDecimal.ZERO, accountDTO.getValue()),
                () -> assertEquals(16, accountDTO.getAccountNumber().length())
        );
    }

    @Test
    void findAll() {
        List<Account> accounts = List.of(ACCOUNT);
        List<AccountDTO> accountDTOS = List.of(MAPPER.toAccountDto(ACCOUNT));
        when(accountRepository.findAllByName(ACCOUNT.getName())).thenReturn(accounts);
        assertEquals(accountDTOS, service.findAll(ACCOUNT.getName()));
    }
}