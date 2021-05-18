package cn.sunyog.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/18 2:04 下午
 * @Desc: 监听redis channel
 */
@Slf4j
@Service
public class RedisChannelListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("====>订阅频道："+new String(message.getChannel()));
        log.info("====>频道数据："+message.toString());
    }
}
