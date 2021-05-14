package cn.sunyog;

import cn.hutool.setting.Setting;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/11 4:49 下午
 * @Desc:
 */
public class CommonUtil {
    private static Setting set=new Setting("config/jedis.properties");

    public static void commonConfig(GenericObjectPoolConfig config){
        config.setMaxIdle(set.getInt("redis.maxIdel"));
        config.setMaxTotal(set.getInt("redis.maxTotal"));
        config.setMaxWaitMillis(set.getLong("redis.maxWaitMillis"));
        config.setMinEvictableIdleTimeMillis(set.getLong("redis.minEvictableIdleTimeMillis"));
        config.setNumTestsPerEvictionRun(set.getInt("redis.numTestsPerEvictionRun"));
        config.setTimeBetweenEvictionRunsMillis(set.getLong("redis.timeBetweenEvictionRunsMillis"));
        config.setTestOnBorrow(set.getBool("redis.testOnBorrow"));
        config.setTestWhileIdle(set.getBool("redis.testWhileIdle"));
    }

    public static String getStrSetting(String key){
        return set.getStr(key);
    }

    public static Integer getIntSetting(String key) {
        return set.getInt(key);
    }
}
