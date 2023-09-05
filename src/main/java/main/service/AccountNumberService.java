package main.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountNumberService {

    private static final int NUMBER_RANGE = 16;
    Random random = new Random();

    public String createAccountNumber() {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < NUMBER_RANGE; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
}
