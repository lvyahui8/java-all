package org.lyh.spring.base.boot.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {


    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.pass}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer
                = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new org.springframework.core.io.Resource[]{new ClassPathResource("redis.properties")};
        propertySourcesPlaceholderConfigurer.setLocations(resourceLocations);
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHost);
        factory.setPort(redisPort);
        factory.setPassword(password);
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    StringRedisSerializer keyRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    GenericToStringSerializer valueRedisSerializer() {
        return new GenericToStringSerializer(String.class);
    }


    @Bean(name = "stringRedisTemplate")
    RedisTemplate<String, String> redisTemplate() {
        final RedisTemplate<String, String> template = new RedisTemplate<String, String>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(keyRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(String.class));
        template.setValueSerializer(valueRedisSerializer());
        return template;
    }

    @Bean(name = "objectRedisTemplate")
    RedisTemplate<String,Object> objectRedisTemplate(){
        final RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(keyRedisSerializer());
        return template;
    }
}