package main.service.mapper;

import main.dto.TransactionInfo;
import main.persistance.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    TransactionInfo toTransactionInfo(Transaction transaction);
}
