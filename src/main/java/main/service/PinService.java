package main.service;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class PinService {

    private static final int PIN_LENGTH = 4;

    public String getSecretPin(String pin) {
        return DigestUtils.md5DigestAsHex(pin.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isPinCorrect(String pin, String secretPin) {
        if (pin == null) {
            return false;
        }
        return getSecretPin(pin).equals(secretPin);
    }

    public boolean isPinAvailable(String pin) {
        return pin.matches(String.format("\\d{%s}", PIN_LENGTH));
    }
}
