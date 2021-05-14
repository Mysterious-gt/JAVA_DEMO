package cn.sunyog;

import cn.hutool.log.StaticLog;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MysteriousGT
 * @Date: 2021/1/20 4:24 下午
 * @Desc:
 */
public class SpringRedisDemo {
    private RedisTemplate template;

    /**
     * 配置redisTemplate
     */
    @Before
    public void configTemplate() {
        //lettuceConfig();
        jedisPoolConfig();
    }

    /**
     * jedis方式配置redisTemplate
     */
    private void jedisPoolConfig() {
        //配置ip:port
        String host = CommonUtil.getStrSetting("redis.host");
        Integer port = CommonUtil.getIntSetting("redis.port");
        Integer database = CommonUtil.getIntSetting("redis.database");
        RedisStandaloneConfiguration redisStandaloneConfig = new RedisStandaloneConfiguration(host, port);
        redisStandaloneConfig.setDatabase(database);
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfig);
        GenericObjectPoolConfig config = factory.getPoolConfig();
        //配置jedisPool参数
        CommonUtil.commonConfig(config);

        this.template = new RedisTemplate();
        this.template.setConnectionFactory(factory);
        this.template.afterPropertiesSet();
        this.template.setValueSerializer(new StringRedisSerializer());
    }

    /**
     * 官方推荐的配置方式
     */
    private void lettuceConfig() {
        String host = CommonUtil.getStrSetting("redis.host");
        Integer port = CommonUtil.getIntSetting("redis.port");
        Integer database = CommonUtil.getIntSetting("redis.database");

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().build();
        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(host, port);
        serverConfig.setDatabase(database);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(serverConfig, clientConfig);
        factory.afterPropertiesSet();//此代码不能少，否则报空指针
        this.template = new RedisTemplate();
        this.template.setConnectionFactory(factory);
        this.template.afterPropertiesSet();
        this.template.setValueSerializer(new StringRedisSerializer());
    }

    @Test
    public void redisStr() {
        this.template.opsForValue().set("key_1", "i am the value of key_1");
        Object val_1 = template.opsForValue().get("key_1");
        StaticLog.info("====>" + val_1);
        Integer append = template.opsForValue().append("key_1", ", and you are shabibi");
        StaticLog.info("====>" + append);

        Object val_2 = template.opsForValue().get("key_1");
        StaticLog.info("====>" + val_2);
        this.template.expire("key_1", 20, TimeUnit.SECONDS);
    }

    @Test
    public void redisHash() {
        this.template.opsForHash().put("hash_key", "hash_1", "abc");
        this.template.opsForHash().put("hash_key", "hash_2", "def");
        Object val_1 = this.template.opsForHash().get("hash_key", "hash_1");
        StaticLog.info("====>" + val_1);

        //获取整个hash对象
        Map hash_val = this.template.opsForHash().entries("hash_key");
        StaticLog.info("====>" + hash_val);

        //插入一整个map
        Map<String, Object> map = new HashMap<>(8);
        map.put("key_1", "value_1");
        map.put("key_2", "value_2");
        map.put("key_3", "value_3");
        map.put("key_4", 5);
        this.template.opsForHash().putAll("hash_key", map);
        Map hash_val2 = this.template.opsForHash().entries("hash_key");
        StaticLog.info("====>" + hash_val2);

        //删除hash中的一个属性，hashKey不能为空
        this.template.opsForHash().delete("hash_key", "hash_1");
        Map hash_val3 = this.template.opsForHash().entries("hash_key");
        StaticLog.info("====>" + hash_val3);

        //删除整个hash对象
        this.template.delete("hash_key");
        Map hash_key4 = this.template.opsForHash().entries("hash_key");
        StaticLog.info("====>" + hash_key4);
    }

    @Test
    public void redisList() {
        this.template.opsForList().leftPushAll("list_key", "abc", "def", "ghi", "jkl");
        this.template.opsForList().leftPush("list_key", "1");
        List list_1 = this.template.opsForList().range("list_key", 0, -1);
        StaticLog.info("====>" + list_1);
        List list_2 = this.template.opsForList().range("list_key", 0, 3);
        StaticLog.info("====>" + list_2);

        this.template.opsForList().rightPush("list_key", "2");
        Object pop_1 = this.template.opsForList().leftPop("list_key");
        StaticLog.info("====>" + pop_1);
        List list_3 = this.template.opsForList().range("list_key", 0, -1);
        StaticLog.info("====>" + list_3);

        this.template.delete("list_key");
    }

    @Test
    public void redisSet() {
        this.template.opsForSet().add("set_key", "abc", "def", "def", "ghikmn", "2", "3");
        Set set_1 = this.template.opsForSet().members("set_key");
        StaticLog.info("====>" + set_1);

        Boolean isMember = this.template.opsForSet().isMember("set_key", "abc");
        StaticLog.info("====>" + isMember);

        this.template.delete("set_key");
    }

    @Test
    public void redisSortedSet() {
        this.template.opsForZSet().add("sort_key", "aaa", 3);
        this.template.opsForZSet().add("sort_key", "abc", 1);
        this.template.opsForZSet().add("sort_key", "bca", 0);
        this.template.opsForZSet().add("sort_key", "bbb", 3);
        Set members_1 = this.template.opsForZSet().range("sort_key", 0, -1);
        StaticLog.info("====>" + members_1);

        Set members_2 = this.template.opsForZSet().rangeByScore("sort_key", 0, 2);
        StaticLog.info("====>" + members_2);
        this.template.delete("sort_key");
    }
}
