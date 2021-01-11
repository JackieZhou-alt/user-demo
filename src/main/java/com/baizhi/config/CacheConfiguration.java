package com.baizhi.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheConfiguration {
    //配置缓存管理器
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory){

        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);

        return new RedisCacheManager(redisCacheWriter,getRedisCacheConfiguration(20),
                getRedisCacheConfigurationMap());
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap(){
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("users", getRedisCacheConfiguration(100));
        redisCacheConfigurationMap.put("books", getRedisCacheConfiguration(60));
        return redisCacheConfigurationMap;
    }
    //RedisCacheConfiguration 用于负责Redis的缓存配置
    private RedisCacheConfiguration getRedisCacheConfiguration(int seconds){
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        return redisCacheConfiguration
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)
                )
                .entryTtl(Duration.ofSeconds(seconds));
    }
}
