package main.config;

import main.persistance.entity.TransactionType;
import main.service.resolvers.TransactionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableMap;

@Configuration
public class TransactionResolversConfig {

    @Bean
    public Map<TransactionType, TransactionResolver> transactionTypeTransactionResolverMap(List<TransactionResolver> resolvers) {
        return resolvers.stream()
                .collect(toUnmodifiableMap(TransactionResolver::getType, resolver -> resolver));
    }

}
