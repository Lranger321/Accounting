package main.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.redis")
@Data
public class RedisProperties {

    private String hostName;
    private Integer port;
}
