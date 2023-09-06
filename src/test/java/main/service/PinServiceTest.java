package main.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PinServiceTest {

    private final String PIN = "1234";
    private final PinService pinService = new PinService();

    @Test
    void isPinCorrect() {
        assertTrue(pinService.isPinCorrect(PIN, pinService.getSecretPin(PIN)));
    }
}