package cn.sunyog.config;

import cn.sunyog.component.RedisChannelListener;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/18 1:35 下午
 * @Desc: redis相关配置
 */
@Slf4j
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.database}")
    private Integer database;
    @Value("${spring.redis.jedis.pool.maxIdle}")
    private Integer maxIdel;
    @Value("${spring.redis.jedis.pool.maxTotal}")
    private Integer maxTotal;
    @Value("${spring.redis.jedis.pool.maxWait}")
    private Long maxWait;
    @Value("${spring.redis.jedis.pool.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${spring.redis.jedis.pool.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;
    @Value("${spring.redis.jedis.pool.timeBetweenEvictionRuns}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${spring.redis.jedis.pool.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${spring.redis.jedis.pool.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${spring.redis.config.listener.channels}")
    private String channels;

    private RedisSerializer getSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(new DefaultBaseTypeLimitingValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL);

        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer<>(String.class);
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }

    private RedisConnectionFactory getConnectFactory() {
        RedisStandaloneConfiguration redisStandaloneConfig = new RedisStandaloneConfiguration(host, port);
        redisStandaloneConfig.setDatabase(this.database);
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfig);
        GenericObjectPoolConfig config = factory.getPoolConfig();
        config.setMaxIdle(this.maxIdel);
        config.setMaxTotal(this.maxTotal);
        config.setMaxWaitMillis(this.maxWait);
        config.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        config.setNumTestsPerEvictionRun(this.numTestsPerEvictionRun);
        config.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        config.setTestOnBorrow(this.testOnBorrow);
        config.setTestWhileIdle(this.testWhileIdle);
        factory.afterPropertiesSet();
        return factory;
    }

    /**
     * 配置redis template
     *
     * @return
     */
    @Bean
    public RedisTemplate getTemplate() {
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(this.getConnectFactory());
        log.info("Redis连接工厂配置完成");
        RedisSerializer serializer = this.getSerializer();
        StringRedisSerializer strSerializer = new StringRedisSerializer();
        template.setKeySerializer(strSerializer);
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(strSerializer);
        template.setHashValueSerializer(serializer);
        log.info("Redis序列化配置完成");
        template.afterPropertiesSet();
        log.info("Redis配置完成");
        return template;
    }

    /**
     * 配置redis消息监听，把redis当作消息队列
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(this.getConnectFactory());

        String[] channelArr = this.channels.split(",");
        for (String channel : channelArr) {
            //监听channel1
            container.addMessageListener(this.getAdapter(), new ChannelTopic(channel));
            log.info("添加Redis Channel监听，Channel="+channel);
        }
        return container;
    }

    private MessageListenerAdapter getAdapter() {
        RedisChannelListener subScript = new RedisChannelListener();
        MessageListenerAdapter adapter = new MessageListenerAdapter(subScript);
        return adapter;
    }
}
