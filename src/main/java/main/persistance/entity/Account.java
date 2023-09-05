package main.persistance.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Account extends AbstractEntity {


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "pin", nullable = false)
    private String pin;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    public void addValue(BigDecimal value) {
        this.value = value.add(value);
    }

    public void minusValue(BigDecimal value) {
        this.value = value.add(value.negate());
    }
}
