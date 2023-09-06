package main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Data
public class AccountDTO {

    private String name;
    private String accountNumber;
    private BigDecimal value;
}
