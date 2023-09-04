package main.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Data
@Entity
public class Transaction extends AbstractEntity {

    private Account from;
    private Account to;
    private BigDecimal value;
}
