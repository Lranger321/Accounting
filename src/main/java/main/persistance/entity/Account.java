package main.persistance.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "pin", nullable = false)
    private String pin;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    public void addValue(BigDecimal value) {
        this.value = this.value.add(value);
    }

    public void minusValue(BigDecimal value) {
        this.value = this.value.subtract(value);
    }
}
