package main.dto;

import main.persistance.entity.TransactionStatus;

import java.math.BigDecimal;

public class TransactionInfo {

    private String accountNumberFrom;
    private String accountNumberTo;
    private BigDecimal value;
    private TransactionType type;
    private TransactionStatus status;
}
