package main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main.persistance.entity.TransactionStatus;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TransactionInfo {

    private AccountDTO account;
    private AccountDTO accountTo;
    private BigDecimal value;
    private TransactionType type;
    private TransactionStatus status;
    private String error;
    private Date createdAt;
}
