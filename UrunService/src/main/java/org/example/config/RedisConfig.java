package org.example.config;



import org.example.entity.Hamburger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig
{
    @Value("${redis.host}")
    String redisHost;
    @Value("${redis.port}")
    int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory()
    {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost,redisPort));
    }

    @Bean
    public RedisTemplate<String, Hamburger> redisTemplate(){
        RedisTemplate<String,Hamburger> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());


        return template;
    }
}
