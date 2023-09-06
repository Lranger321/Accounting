package main.persistance.entity;

import lombok.*;
import main.dto.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Account account;

    @ManyToOne
    @JoinColumn(name = "account_transfer_to")
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

    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}
