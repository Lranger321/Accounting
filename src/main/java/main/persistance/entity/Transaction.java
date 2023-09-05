package main.persistance.entity;

import lombok.*;
import main.dto.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Transaction extends AbstractEntity {

    @ManyToOne
    private Account account;

    @ManyToOne
    private Account accountTransferTo;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "error")
    private String error;
}
