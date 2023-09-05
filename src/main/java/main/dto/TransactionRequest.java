package main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class TransactionRequest {

    private String account;
    private String accountTo;
    private String value;
}
