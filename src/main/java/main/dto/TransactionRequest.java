package main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
public class TransactionRequest {

    private String account;
    private String accountTransferTo;
    private String pin;
    private BigDecimal value;
}
