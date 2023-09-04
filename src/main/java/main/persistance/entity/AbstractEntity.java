package main.persistance.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private UUID id;
}
