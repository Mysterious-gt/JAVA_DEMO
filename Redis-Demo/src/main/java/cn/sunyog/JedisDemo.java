package cn.sunyog;

import cn.hutool.core.lang.Console;
import cn.hutool.log.StaticLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @Author: MysteriousGT
 * @Date: 2021/1/20 4:23 下午
 * @Desc: jedis
 */
public class JedisDemo {
    private JedisPool pool;

    /**
     * 配置RedisPool连接池
     */
    @Before
    public void configRedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        CommonUtil.commonConfig(config);

        String host = CommonUtil.getStrSetting("redis.host");
        Integer port = CommonUtil.getIntSetting("redis.port");
        this.pool = new JedisPool(config, host, port);
    }

    /**
     * 关闭连接池
     */
    @After
    public void after(){
        if(!this.pool.isClosed()){
            this.pool.close();
        }
    }

    /**
     * redis string
     * @throws InterruptedException
     */
    @Test
    public void redisStr() throws InterruptedException {
        Jedis jedis = this.pool.getResource();
        //jedis.auth("1234zzzz");
        jedis.set("testKey01", "textValue01");
        String res = jedis.get("testKey01");
        StaticLog.info("====>"+res);
        jedis.append("testKey01", "appendStr");
        res = jedis.get("testKey01");
        StaticLog.info("====>"+res);
        //设置超时时间
        jedis.expire("testKey01", 2);
        Thread.sleep(2000);
        String val = jedis.get("testKey01");
        StaticLog.info("====>"+val);
    }

    /**
     * redis hash
     */
    @Test
    public void redisHash() {
        Jedis jedis = this.pool.getResource();
        Map<String, String> map = new HashMap(16);
        map.put("key1", "value1");
        map.put("key2", "1234");
        map.put("key3", new Date().toString());
        jedis.hmset("hashKey", map);

        Map<String, String> resMap = jedis.hgetAll("hashKey");
        StaticLog.info("====>"+resMap);

        List<String> mapVals = jedis.hmget("hashKey", "key1", "key2");
        StaticLog.info("====>"+mapVals);
        jedis.del("hashKey");
    }

    /**
     * redis list
     */
    @Test
    public void redisList() {
        Jedis jedis = this.pool.getResource();
        Long lpush = jedis.lpush("listKey", "val01", "val02");
        StaticLog.info("====>"+lpush);

        jedis.lpush("listKey", "val03","val04");
        jedis.rpush("listKey","val00");
        List<String> strs = jedis.lrange("listKey", 0, -1);
        StaticLog.info("====>"+strs);
        List<String> strs_2 = jedis.lrange("listKey", 0, 1);
        StaticLog.info("====>"+strs_2);

        jedis.expire("listKey",20);
    }

    /**
     * redis set
     */
    @Test
    public void redisSet() {
        Jedis jedis = this.pool.getResource();
        jedis.sadd("setKey", "member1", "member2");
        Set<String> setKeys = jedis.smembers("setKey");
        StaticLog.info("====>"+setKeys);

        //判断是否存在
        Boolean hasMember = jedis.sismember("setKey", "member1");
        Boolean hasMember_2 = jedis.sismember("setKey", "member10");
        StaticLog.info("====>"+hasMember+"\t"+hasMember_2);

        Long len = jedis.scard("setKey");
        StaticLog.info("====>"+len);
        jedis.expire("setKey", 2);
    }

    /**
     * redis sort set
     */
    @Test
    public void redisSortSet() {
        Jedis jedis = this.pool.getResource();
        jedis.zadd("sortedSet", 1, "sortedSet1");
        jedis.zadd("sortedSet", 1, "sortedSet2-1");
        jedis.zadd("sortedSet", 2, "sortedSet2");
        Set<String> sortedSets = jedis.zrange("sortedSet", 0, -1);
        StaticLog.info("====>"+sortedSets);

        //倒叙
        Set<String> sortSet2 = jedis.zrevrange("sortedSet", 0, -1);
        StaticLog.info("====>"+sortSet2);
        //获取score小于1.1的元素
        Set<Tuple> tuples = jedis.zrangeByScoreWithScores("sortedSet", 0, 1.1);
        StaticLog.info("====>"+tuples);
        jedis.expire("sortedSet", 2);
    }

    /**
     * 发布订阅模式
     */
    @Test
    public void publishSubscribe() throws InterruptedException {
        Jedis client = this.pool.getResource();
        Thread thread = new SubScriptThread(this.pool);
        StaticLog.info("====>"+thread.getState());
        thread.start();
        StaticLog.info("====>"+thread.getState());
        Set<String> keys = client.keys("*");
        StaticLog.info("====>"+keys);
        //等待线程执行完毕
        StaticLog.info("====>"+thread.getState());
        Console.log("====>main thread sleep");
        Thread.sleep(600000);
    }

    class JedisPubSubClass extends JedisPubSub{
        public void onMessage(String channel, String message) {
            StaticLog.info("publish: onMessage====>"+message);
        }

        public void onPMessage(String pattern, String channel, String message) {
            StaticLog.info("publish: onPMessage====>"+message);
        }

        public void onSubscribe(String channel, int subscribedChannels) {
        }

        public void onUnsubscribe(String channel, int subscribedChannels) {
        }

        public void onPUnsubscribe(String pattern, int subscribedChannels) {
        }

        public void onPSubscribe(String pattern, int subscribedChannels) {
        }

        public void onPong(String pattern) {
            StaticLog.info("publish:====>pong");
        }
    }

    class SubScriptThread extends Thread{
        private JedisPool pool;
        public SubScriptThread(JedisPool pool) {
            super("SubScriptThread");
            this.pool=pool;
        }

        @Override
        public void run() {
            super.run();
            Jedis jedis=null;
            try {
                jedis = this.pool.getResource();
                JedisPubSub jedisPub = new JedisPubSubClass();
                jedis.subscribe(jedisPub, "channel1");
            }finally {
                if(jedis!=null){
                    jedis.close();
                }
            }
        }
    }
}
