package main.persistance.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

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
}
