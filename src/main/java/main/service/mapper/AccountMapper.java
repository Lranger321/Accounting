package main.service.mapper;

import main.dto.AccountDTO;
import main.persistance.entity.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    AccountDTO toAccountDto(Account account);
}
