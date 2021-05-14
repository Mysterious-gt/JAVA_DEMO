# Redis异常

## 连接相关异常

### JedisDataException

```text
redis.clients.jedis.exceptions.JedisDataException: 
DENIED Redis is running in protected mode because protected mode is enabled, no bind address was specified, 
no authentication password is requested to clients. In this mode connections are only accepted from the loopback interface. 
If you want to connect from external computers to Redis you may adopt one of the following solutions: 
1) Just disable protected mode sending the command 'CONFIG SET protected-mode no' from the loopback 
interface by connecting to Redis from the same host the server is running, 
however MAKE SURE Redis is not publicly accessible from internet if you do so. Use CONFIG REWRITE to make this change permanent. 
2) Alternatively you can just disable the protected mode by editing the Redis configuration file, 
and setting the protected mode option to 'no', and then restarting the server. 
3) If you started the server manually just for testing, restart it with the '--protected-mode no' option. 
4) Setup a bind address or an authentication password. NOTE: You only need to do one of the above things 
in order for the server to start accepting connections from the outside.
```
Redis服务运行在保护模式中，此客户端IP地址没有绑定，所以无法访问，可关闭Redis保护模式，或添加IP地址解决
```shell script
# 关闭保护模式
config set protected-mode no

# 修改redis配置文件后重启redis
vim /app/redis-5.0.10/redis.conf
bind 192.168.1.100 10.0.0.1 127.0.0.1 #在空格后添加IP
# 关闭redis
ps -ef|grep redis
kill -9 {PID} #或在redis-cli中执行shutdown命令
redis-server /app/redis-5.0.10/redis.conf #指定配置文件启动redis
```

### JedisConnectionException

```text
redis.clients.jedis.exceptions.JedisConnectionException: Failed connecting to ip:port
```
Redis服务未启动报错，启动redis服务即可

### RedisTemplate配置后，使用redisTemplate报错

```text
java.lang.NullPointerException
	at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$SharedConnection
    .getNativeConnection(LettuceConnectionFactory.java:957)
```
RedisTemplate中的LettuceConnectionFactory缺少配置，添加如下配置项后可解决
```
factory.afterPropertiesSet();
```

### RedisTemplate的append()方法失效

没有配置Value序列化方式，配置后可解决，在配置方法中添加如下配置：
```
this.template.setValueSerializer(new StringRedisSerializer());
```