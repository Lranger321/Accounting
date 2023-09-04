package main.persistance.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Data
@Entity
public class Account extends AbstractEntity {

    private UUID id;
    private String name;
    private String accountNumber;
    private String pin;
}
