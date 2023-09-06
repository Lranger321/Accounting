package main.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PinServiceTest {

    private static final String PIN = "1234";
    private final PinService pinService = new PinService();

    public static Stream<Arguments> isPinAvailableSource() {
        return Stream.of(
                Arguments.of("1234", true),
                Arguments.of("32145", false),
                Arguments.of("412e", false),
                Arguments.of("123,", false)
        );
    }

    @Test
    void isPinCorrect() {
        assertTrue(pinService.isPinCorrect(PIN, pinService.getSecretPin(PIN)));
    }

    @ParameterizedTest
    @MethodSource("isPinAvailableSource")
    void isPinAvailable(String pin, boolean isAvailable) {
        assertEquals(isAvailable, pinService.isPinAvailable(pin));
    }


}