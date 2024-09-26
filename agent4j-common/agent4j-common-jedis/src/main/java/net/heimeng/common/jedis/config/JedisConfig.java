package net.heimeng.common.jedis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Optional;

/**
 * Redis 配置项
 *
 * @author InwardFlow
 */
@Data
@Configuration
@EnableCaching
@ConfigurationProperties("spring.data.redis")
public class JedisConfig {

    private String host;
    private int port;
    private Duration timeout;
    private String password;
    private int database;

    @Bean(destroyMethod = "close")
    public Jedis jedis() {
        Jedis jedis = new Jedis(host, port);
        jedis.auth(Optional.ofNullable(password).orElse(""));
        jedis.getClient().setSoTimeout(timeout.toMillisPart());
        jedis.select(database);
        return jedis;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                .transactionAware()
                .build();
    }

}
