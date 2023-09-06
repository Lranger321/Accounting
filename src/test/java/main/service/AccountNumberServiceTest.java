package main.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountNumberServiceTest {

    private final AccountNumberService accountNumberService = new AccountNumberService();

    @Test
    void createAccountNumber() {
        assertEquals(16, accountNumberService.createAccountNumber().length());
    }
}
