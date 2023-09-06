package main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class TransactionRequest {

    private String account;
    private String accountTransferTo;
    private String pin;
    private BigDecimal value;
}
